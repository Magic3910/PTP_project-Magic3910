import { Actor, bedrockServer, command, NetworkIdentifier } from "bdsx";
import { IdByName, NameById, playerPermission } from "../Module_all";
import { CANCEL, MinecraftPacketIds, netevent, nethook, PacketId } from "bdsx";
import { open, readFile, readFileSync, writeFileSync } from "fs";
import { DataById, Formsend, getScore, sendText } from "../Module_all/2913Module";
import { events } from 'bdsx/event';
import { system } from "../example_and_test/bedrockapi-system";
let localfile = "Titles.json";
let localfile2 = "PLTitles.json";
let localfile3 = "HVTitles.json";
let TitleJs:any[] = [];
let PTitleJs:any[] = [];
let HVTitleJs:any[] = [];
let dummyG = [{
    Name: '-',
    Buy: '0'
}]
let dummyS = [{
    Name: '-',
    cldgh: '-'
}]

let dummyL = [{
    Name: '-',
    cldgh: '-'
}]
open(localfile,'a+',function(err:any,fd:any){
    if(err) throw err;
    try {
        JSON.parse(readFileSync(localfile, "utf8"));
    } catch (err) {
        writeFileSync(localfile, JSON.stringify(dummyG), "utf8")
    }
    TitleJs = JSON.parse(readFileSync(localfile, "utf8"));
});
open(localfile3,'a+',function(err:any,fd:any){
    if(err) throw err;
    try {
        JSON.parse(readFileSync(localfile3, "utf8"));
    } catch (err) {
        writeFileSync(localfile3, JSON.stringify(dummyL), "utf8")
    }
    HVTitleJs = JSON.parse(readFileSync(localfile3, "utf8"));
});
open(localfile2,'a+',function(err:any,fd:any){
    if(err) throw err;
    try {
        JSON.parse(readFileSync(localfile2, "utf8"));
    } catch (err) {
        writeFileSync(localfile2, JSON.stringify(dummyS), "utf8")
    }
    PTitleJs = JSON.parse(readFileSync(localfile2, "utf8"));
});

command.register('칭호', `§e칭호 관리창을 엽니다.`).overload((param, origin)=>{
    let Per = playerPermission(origin.getName());
    if (Per == 'operator') {
        let playerNetID = IdByName(origin.getName());
        Otitle(playerNetID);
    }
    if (Per == 'member') {
        let playerNetID = IdByName(origin.getName());
        MTitle(playerNetID);
    }
},{});

import { ScorePacketInfo, SetDisplayObjectivePacket, SetScorePacket } from "bdsx/bds/packets";
import { playerL } from "./playerlist";



function MTitle(target:NetworkIdentifier) {

    Formsend(target, {
        type: "form",
        title: "칭호 설정",
        content: '',
        buttons: [
            {
                "text": "칭호 설정"
            },
            {
                "text": "칭호 구매"
            }
        ]
    }, data => {
        if (data == 0) LTitle(target);
        if (data == 1) Buy(target);
    });
};

function Buy(target: NetworkIdentifier) {
    let Arr:any[] = [];
    PTitleJs = JSON.parse(readFileSync(localfile2, "utf8"));
    TitleJs = JSON.parse(readFileSync(localfile, "utf8"));
    TitleJs.forEach((value) => {
        let j = {
            text: `${value.Name}\n${value.Buy}원`
        }
        Arr.push(j);
    });
    Formsend(target, {
        type: "form",
        title: "칭호",
        content: "구매 가능 칭호가 모두 표시됩니다.",
        buttons: Arr
    }, data => {
        if (data == null) return;
        let money = TitleJs[data].Buy;
        system.executeCommand(`scoreboard players add @a[name="${NameById(target)}",c=1] gold 0`, result => {
            // @ts-ignore
            let msgs = result.data.statusMessage;
            let msg = String(msgs).split('now');
            let a = String(msg[1]);
            let s = 0;
            if (a.includes('-') === true) s = Number(a.replace(/[^0-9  ]/g, '')) - (Number(a.replace(/[^0-9  ]/g, '')) * 2);
            if (a.includes('-') === false) s = Number(a.replace(/[^0-9  ]/g, ''));
            console.log(s);
            let b = s;
            let T = TitleJs[data].Name;
            Formsend(target, {
                type: "form",
                title: `${TitleJs[data].Name}를(을) 구매 하시겠습니까. `,
                content: `현재 소지금 : ${s}원`,
                buttons: [
                    {
                        "text": "구매하기"
                    }
                ]
            }, data => {
                if (data == null) return;
                if (b >= money) {
                    bedrockServer.executeCommand(`/scoreboard players remove @a[name="${NameById(target)}",c=1] gold ${money}`)
                    let PutJs = {
                        Name: `${NameById(target)}`,
                        cldgh: `${T}`
                    }
                    console.log(JSON.stringify(PutJs));
                    PTitleJs.push(PutJs);
                    tellRaw(`${NameById(target)}`, '칭호를 성공적으로 구매하였습니다.')
                    writeFileSync(localfile2, JSON.stringify(PTitleJs), "utf8")
                }
                if (b < money) tellRaw(`${NameById(target)}`, '돈이 부족합니다.');
            });
        });
    });
}

function Otitle(target:NetworkIdentifier) {
    Formsend(target, {
        type: "form",
        title: "칭호 설정 (관리자 전용)",
        content: '',
        buttons: [
            {
                "text": "칭호 생성"
            },
            {
                "text": "칭호 삭제"
            },
            {
                "text": "칭호 설정"
            },
            {
                "text": "칭호 구매"
            },
            {
                "text": "칭호 지급"
            },
            {
                "text": "칭호 뺏기"
            }
        ]
    }, data => {
        if (data == 0) Ntitle(target);
        if (data == 1) DTitle(target);
        if (data == 2) LTitle(target);
        if (data == 3) Buy(target);
        if (data == 4) Give(target);
        if (data == 5) Delect(target);
    });
};
function Give(target:NetworkIdentifier) {
    let Ar:any[] = [];

    playerL.forEach((value) => {
        let j = {
            text: `${value.Name}`
        }
        Ar.push(j);
    });
    Formsend(target, {
        type: "form",
        title: "칭호",
        content: "칭호를 지급할 상대를 결정하세요",
        buttons: Ar
    }, data => {
        if (data == null) return;
        let Tname = playerL[data].Name;
        Formsend(target, {
            type: "custom_form",
            title: "칭호 추가",
            content: [
                {
                    "type": "input",
                    "text": "칭호 이름",
                    "placeholder": "칭호 이름을 적어주세요."
                }
            ]
        }, data => {
            let N = data;
            Formsend(target, {
                type: "form",
                title: "",
                content: `${Tname}에게 ${N}칭호를 지급하시겠습니까?`,
                buttons: [{"text":"지급하기"},{"text":"취소"}]
            }, data => {
                if (data == null) return;
                if (data == 1) return;
                if (data == 0) {
                    PTitleJs = JSON.parse(readFileSync(localfile2, "utf8"));
                    let PutJs = {
                        Name: `${Tname}`,
                        cldgh: `${N}`
                    }
                    console.log(JSON.stringify(PutJs));
                    PTitleJs.push(PutJs);
                    tellRaw(`${NameById(target)}`, '칭호를 성공적으로 지급하였습니다.')
                    tellRaw(`${Tname}`, `§a관리자 §e${NameById(target)}§a에게 §f[ ${N} ]§f§a칭호를 지급받았습니다.`)
                    writeFileSync(localfile2, JSON.stringify(PTitleJs), "utf8")
                }
            })
        });
    });
}
function Delect(target:NetworkIdentifier) {
    let Ar:any[] = [];
    playerL.forEach((value) => {
        let j = {
            text: `${value.Name}`
        }
        Ar.push(j);
    });
    Formsend(target, {
        type: "form",
        title: "칭호 뺏기",
        content: "칭호를 뺏을 상대를 결정하세요",
        buttons: Ar
    }, data => {
        let Tname = playerL[data].Name;
        let Arr:any[] = [];
        PTitleJs = JSON.parse(readFileSync(localfile2, "utf8"));
        let targetl = PTitleJs.filter((e:any) => e.Name == Tname)
        console.log(targetl);
        targetl.forEach((value) => {
            let j = {
                text: `${value.cldgh}`,
            }
            Arr.push(j);
        });
        Formsend(target, {type: "form",
            title: "칭호",
            content: "대상의 칭호 목록이 모두 표시됩니다.",
            buttons: Arr
            }, data => {
                if (data == null) return;
                let m = targetl[data].cldgh;
                Formsend(target, {
                    type: "form",
                    title: "",
                    content: `§f${Tname}§f의 [§f ${m} §f] 칭호를 삭제하시겠습니까?`,
                    buttons: [{"text":"네"},{"text":"아니요"}]
                }, data => {
                    if(data == null) return;
                    if (data == 0) {
                        HVTitleJs = JSON.parse(readFileSync(localfile3, "utf8"));
                        let targetjs = HVTitleJs.filter((es:any) => es.Name == Tname);
                        let js = targetjs.filter((es:any) => es.cldgh == m);
                        let states = HVTitleJs.indexOf(js);
                        HVTitleJs.splice(states, 1);
                        PTitleJs = JSON.parse(readFileSync(localfile2, "utf8"));
                        let targetjs1 = HVTitleJs.filter((es:any) => es.Name == Tname);
                        let js1 = targetjs1.filter((es:any) => es.cldgh == m);
                        let state1 = HVTitleJs.indexOf(js1);
                        PTitleJs.splice(state1, 1);
                        writeFileSync(localfile2, JSON.stringify(PTitleJs), "utf8")
                        writeFileSync(localfile3, JSON.stringify(HVTitleJs), "utf8")
                        tellRaw(`${Tname}`, `관리자 ${NameById(target)}님께서 ${m}칭호를 뺏어가셨습니다.`)
                    }
                    if (data == 1) return;
                });
            });
    });
}
function DTitle(target:NetworkIdentifier) {
    let Ar:any[] = [];
    TitleJs = JSON.parse(readFileSync(localfile, "utf8"));
    TitleJs.forEach((value) => {
        let j = {
            text: `${value.Name}`
        }
        Ar.push(j);
    });
    Formsend(target, {
        type: "form",
        title: "칭호",
        content: "삭제할 칭호를 선택하세요",
        buttons: Ar
    }, data => {
        if (data == null) return;
        for(let i = 0; i < Ar.length; i++) {
            if(Ar[i].text === `${TitleJs[data].Name}`)  {
                if(Ar[i].text != `${TitleJs[0].Name}`) {
                    TitleJs.splice(i, 1);
                    writeFileSync(localfile, JSON.stringify(TitleJs), "utf8")
                    tellRaw(NameById(target), `§e칭호가 삭제되었습니다.`);
                    break;
                }
                if(Ar[i].text == `${TitleJs[0].Name}`) {
                    tellRaw(NameById(target), `§e삭제할 수 없는 칭호입니다.`);
                }
            }
        }
    });
}

function Ntitle(target:NetworkIdentifier) {
    Formsend(target, {
        type: "custom_form",
        title: "칭호 추가",
        content: [
            {
                "type": "input",
                "text": "칭호 이름",
                "placeholder": "칭호 이름을 적어주세요."
            },
            {
                "type": "input",
                "text": "칭호 가격",
                "placeholder": "칭호 가격을 적어주세요."
            }
        ]
    }, data => {
        if (data == null) return;
        TitleJs = JSON.parse(readFileSync(localfile, "utf8"));
        let [input, b] = data;
        let Tname = String(input);
        Formsend(target, {
            type: "form",
            title: "",
            content: `${Tname}으로 칭호를 만드시겠습니까?\n${b}원`,
            buttons: [{"text":"만들기"}]
        }, data => {
            let objL = TitleJs.map((e:any, i:any) => e.Name);
            if (objL.includes(Tname)) {
                sendText(target, '§c§l이미 존재하는 칭호입니다!', 0);
                return;
            }
            if (objL.includes(Tname) == false) {
                if (TitleJs[0].Name == '-') {
                    TitleJs.splice(0, 1);
                }
                let SetT = Tname;
                let js = {
                    Name: `${SetT}`,
                    Buy: `${b}`
                }
                TitleJs.push(js);
                sendText(target, `§a§l성공적으로 칭호를 추가했습니다.\n§f${Tname}`, 0);
                writeFileSync(localfile, JSON.stringify(TitleJs), "utf8")
            }
        })
    });
};

function LTitle(target: NetworkIdentifier) {
    let Arr:any[] = [];
    PTitleJs = JSON.parse(readFileSync(localfile2, "utf8"));
    let targetl = PTitleJs.filter((e:any) => e.Name == NameById(target))
    console.log(targetl);
    targetl.forEach((value) => {
        let j = {
            text: `${value.cldgh}`,
        }
        Arr.push(j);
    });
    Formsend(target, {
        type: "form",
        title: "칭호",
        content: "칭호 목록이 모두 표시됩니다.",
        buttons: Arr
    }, data => {
        if (data == null) return;
        HVTitleJs = JSON.parse(readFileSync(localfile3, "utf8"));
        let targetjs = HVTitleJs.filter((es:any) => es.Name == NameById(target))[0];
        let states = HVTitleJs.indexOf(targetjs);
        let PutJs = {
            Name: `${NameById(target)}`,
            cldgh: `${targetl[data].cldgh}`
        }
        console.log(targetl[data].cldgh);
        if (targetjs != "undefined") {
            HVTitleJs.splice(states, 1, PutJs)
        }
        if (targetjs == "undefined") {
            HVTitleJs.push(PutJs);
        }
        writeFileSync(localfile3, JSON.stringify(HVTitleJs), "utf8")
        tellRaw(NameById(target), `§e칭호가 ${targetl[data].cldgh}으로 설정되었습니다.`);
    });
}

events.packetBefore(MinecraftPacketIds.Text).on(ev => {
    HVTitleJs = JSON.parse(readFileSync(localfile3, "utf8"));
    let targetjz = HVTitleJs.filter((e:any) => e.Name == ev.name)[0];
    let state = HVTitleJs.indexOf(targetjz);
    let name = ev.name;
    let mes = ev.message;
    if (String(state) != "-1") {
        system.executeCommand(`scoreboard players add @a[name="${ev.name}",c=1] level 0`, result => {
            // @ts-ignore
            let msgs = result.data.statusMessage;
            let msg = String(msgs).split('now');
            let a = String(msg[1]);
            let s = null;
            if (a.includes('-') === true) s = Number(a.replace(/[^0-9  ]/g, '')) - (Number(a.replace(/[^0-9  ]/g, '')) * 2);
            if (a.includes('-') === false) s = Number(a.replace(/[^0-9  ]/g, ''));
            let message = `[${HVTitleJs[state].cldgh}§f] [Lv.${s}] <${name}> : `+mes;
            tellRaw('@a', message);
        });
        return CANCEL;
    }
    if (state == -1) {
        let name = ev.name;
        let mes = ev.message;
        system.executeCommand(`scoreboard players add @a[name="${ev.name}",c=1] level 0`, result => {
            // @ts-ignore
            let msgs = result.data.statusMessage;
            let msg = String(msgs).split('now');
            let a = String(msg[1]);
            let s = null;
            if (a.includes('-') === true) s = Number(a.replace(/[^0-9  ]/g, '')) - (Number(a.replace(/[^0-9  ]/g, '')) * 2);
            if (a.includes('-') === false) s = Number(a.replace(/[^0-9  ]/g, ''));
            tellRaw('@a', `[Lv.${s}] <${name}> : ${mes}`);
        });
        return CANCEL;
    }
});

function tellRaw(playerName: string, text: string){
    bedrockServer.executeCommand(`/tellraw ${playerName} {"rawtext":[{"text":"${text}"}]}`)
}


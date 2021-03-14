import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipInputStream;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.List;
import zipUtil.CompressZip;

public class PTP_MAIN extends JFrame implements ActionListener{
	public static boolean count = false;
	public static String kyonglo = "null"; // ?아 파일경로
	public static String input_k = "null"; //최종 저장 폴더 결정
	public static boolean starting = false;
	public static String Set_mcfuntion = "";
	public static String Set_x = "";
	public static String Set_y = "";
	public static int Set_k = 1;
	public static String Particle = "minecraft:end_rod";
	public static String Name = "@s";
	public static String FN = "output.mcfunction";
	public static boolean blueberry_babo = false; //블벨바보
	public static String BN = "행동팩";
	public static String RN = "리소스팩";
	
	
	Font f1;
	JLabel Set_K_N,Pars,SetNs,SetNss;
	JTextField tf1,tf2,tf3,tf4,sajin_k,Set_K,Par,SetN,SetFN,SetFNs,SetFNss;
	JButton bt1,bt2,bt3,start,Set_K_U,Set_K_D,RUN,OK,SetN_O,SetFN_O,SetFNs_O,RUN_T,RUN_C,SetFNss_O;
	
	/**
	 * 
	 */
	PTP_MAIN(){
		
		
		
		
		
		
		
		//JTextField
		tf1 = new JTextField();
		tf1.setBounds(50,70,400,40);
		tf1.setFont(f1);
		
		
		tf2 = new JTextField();
		tf2.setBounds(50,70,400,40);
		tf2.setFont(f1);
		tf2.setVisible(false);
		tf2.setEditable(false);
		
		
		
		tf3 = new JTextField("경로 미확인");
		tf3.setBounds(50,190,370,40);
		tf3.setEditable(false);
		tf3.setFont(f1);
		
		tf4 = new JTextField("null");
		tf4.setBounds(200,130,370,40);
		tf4.setEditable(false);
		tf4.setVisible(false);
		tf4.setFont(f1);
		
		sajin_k = new JTextField("null"); //2차 사진 경로 표시
		sajin_k.setBounds(50,50,900,40);
		sajin_k.setEditable(false);
		sajin_k.setVisible(false);
		sajin_k.setFont(f1);
 
		
		bt1 = new JButton("경로 확인");
		bt1.setBounds(50, 270, 100, 50);
		bt1.setFont(f1);
 
		bt2 = new JButton("PTP실행");
		bt2.setBounds(160, 270, 100, 50);
		bt2.setFont(f1);
		
		bt3 = new JButton("저장 경로 지정");
		bt3.setBounds(50, 130, 150, 40);
		bt3.setFont(f1);
		bt3.setVisible(false);
		bt3.setEnabled(false);
		
		RUN = new JButton("저장");
		RUN.setBounds(250, 450, 100, 50);
		RUN.setFont(f1);
		RUN.setVisible(false);
		RUN.setEnabled(false);
		
		RUN_T = new JButton("컬러 저장");
		RUN_T.setBounds(150, 450, 100, 50);
		RUN_T.setFont(f1);
		RUN_T.setVisible(false);
		RUN_T.setEnabled(false);
		
		RUN_C = new JButton("컬러 저장");
		RUN_C.setBounds(250, 450, 100, 50);
		RUN_C.setFont(f1);
		RUN_C.setVisible(false);
		RUN_C.setEnabled(false);
		
		start = new JButton("실행");
		start.setBounds(50, 180, 300, 40);
		start.setFont(f1);
		start.setVisible(false);
		start.setEnabled(false);
 
		// ActionListener
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		bt3.addActionListener(this);
		start.addActionListener(this);
		RUN.addActionListener(this);
		RUN_T.addActionListener(this);
		
		Set_K = new JTextField("1");
		Set_K.setBounds(300,150,150,50);
		Set_K.setFont(f1);
		Set_K.setVisible(false);
		Set_K.setEnabled(false);
		
		Set_K_N = new JLabel();
		Set_K_N.setBounds(100, 150, 250, 50);
		Set_K_N.setText("넓이 조정 1/10");
		Set_K_N.setVisible(false);
		
		
		
		
		//JFrame
		add(tf1);add(tf2);add(tf3);add(tf4);add(sajin_k);
		add(bt1);add(bt2);add(bt3);add(start);add(Set_K);add(RUN);
		add(Set_K_N);add(RUN_T);
		setTitle("PTP_Project_MAIN");
		setSize(500,700);
		setLayout(null);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new PTP_MAIN();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String str1 = tf1.getText();
		if(e.getSource() == bt1) {
			File f = new File(str1);
			if(f.isFile()) {
				if(f.exists()) {
					tf3.setText("경로가 확인되었습니다.");
					count = true;
				} else {
					tf3.setText("파일이 존재하지 않습니다.");
					count = false;
				}
			} else {
				tf3.setText("파일이 아닙니다.");
				count = false;
			}
		} else if (e.getSource() == bt2) {
			if (count == false) {
				tf3.setText("경로 확인을 해주십시오.");
			} else {
				tf3.setText("ITP ON");
				bt2.setEnabled(false);
				bt1.setEnabled(false);
		        String old_name = str1;
		        kyonglo = str1;
		        String new_name = "input.png";
		        FileInputStream fin = null;
				try {
					fin = new FileInputStream(old_name);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        FileOutputStream fout = null;
				try {
					fout = new FileOutputStream(new_name);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        int tmp = 0;
		        try {
					while ((tmp = fin.read()) != -1) {
					    fout.write(tmp);                
					    }
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        try {
					fin.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        try {
					fout.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		        bt1.setVisible(false);
		        bt2.setVisible(false);
		        tf1.setVisible(false);
		        tf3.setVisible(false);
		        tf1.setEnabled(false);
		        tf3.setEnabled(false);
		        tf2.setVisible(true);
		        tf2.setText("경로 : "+kyonglo);
		        bt3.setVisible(true);
		        bt3.setEnabled(true);
		        start.setVisible(true);
		        start.setEnabled(true);
		        setSize(1000,700);
		        setTitle("PTP - by magic V.0.0.1 BETA");
			}
		}
		if (e.getSource() == bt3) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.showDialog(this, null);

			File dir = chooser.getSelectedFile();

			if (dir != null) {
				input_k = dir.getPath();
				File file = new File(input_k);
				if (file.isDirectory()) {
					String[] files = file.list();
			        if (files.length > 0) {
			        	int result = 0;
			        	result = JOptionPane.showConfirmDialog(null, "파일이 비어있지 않습니다.\n계속 진행하겠습니까?", "＊경고＊", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			        	if (result == 0) {
			        		tf4.setVisible(true);
							tf4.setText(dir.getPath());
							input_k = dir.getPath();
							starting = true;
			        	} else if (result == 1) {
			        		tf4.setVisible(true);
			        		var test_tf4 = tf4.getText();
			        		if (test_tf4 == "null") {
			        			tf4.setText("올바르지 못한 경로");
			        			starting = false;
			        		}
			        	}
			        	
			        }
			        if (files.length < 1) {
			        	tf4.setVisible(true);
						tf4.setText(dir.getPath());
						input_k = dir.getPath();
						starting = true;
			        }
			    }
			} else if (dir == null) {
				tf4.setVisible(true);
				tf4.setText("올바르지 못한 경로");
				starting = false;
			}
		}
		if (e.getSource() == start) {
			if (starting == true) {
				start.setText("실행!");
				try {
					Thread.sleep(3);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				bt3.setVisible(false);
				bt3.setEnabled(false);
				start.setVisible(false);
				start.setEnabled(false);
				tf2.setVisible(false);
				tf2.setEnabled(false);
				tf4.setVisible(false);
				tf4.setEnabled(false);
				sajin_k.setVisible(true);
				sajin_k.setText("사진 경로 : "+kyonglo+"\n저장경로 : "+input_k);
				
				
				Set_K_D = new JButton("<");
				Set_K_D.setBounds(200, 150, 100, 50);
				Set_K_D.setFont(f1);
				
				Set_K_U = new JButton(">");
				Set_K_U.setBounds(450, 150, 100, 50);
				Set_K_U.setFont(f1);
				
				Set_K_D.addActionListener(this);
				Set_K_U.addActionListener(this);
				Set_K.setEnabled(false);
				
				add(Set_K_D);add(Set_K_U);
				
				Set_K.setVisible(true);
				
				Set_K_N.setVisible(true);
				
				RUN.setVisible(true);
				RUN.setEnabled(true);
				
				RUN_T.setVisible(true);
				RUN_T.setEnabled(true);
				}
				if (starting == false) {
					start.setText("저장 경로를 확인해 주세요.");
					try {
						Thread.sleep(2);
						start.setText("실행");
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		}
		if (e.getSource() == Set_K_D) {
			if (Set_k > 1) {
				Set_k -= 1;
				String to = Integer.toString(Set_k);
				Set_K.setText(to);
			}
		}
		if (e.getSource() == Set_K_U) {
			if (Set_k < 50) {
				Set_k += 1;
				String tos = Integer.toString(Set_k);
				Set_K.setText(tos);
			}
		}
		if (e.getSource() == RUN) {

			new Setting();
			
			
			Set_K_D.setEnabled(false);
			Set_K_U.setEnabled(false);
			RUN.setEnabled(false);
			
			
		}
		if (e.getSource() == RUN_T) {
			blueberry_babo = true;
			Set_K_D.setEnabled(false);
			Set_K_U.setEnabled(false);
			RUN.setEnabled(false);
			RUN_T.setEnabled(false);
			new Settings();
		}
	}
	public static BufferedImage loadImage(String url) {  

	    BufferedImage image = null;  

	    try {  

	        image = ImageIO.read(new File(url));  

	    } catch (IOException e) {  

	        e.printStackTrace();  

	    }  

	    return image;  

	}
	class Setting extends JDialog {
		public Setting() {
			Par = new JTextField(Particle);
			Par.setBounds(10, 20, 150, 20);
			Par.setFont(f1);
			Par.setVisible(true);
			Par.setEditable(true);
			OK = new JButton("확인");
			OK.setBounds(10, 50, 70, 30);
			OK.setFont(f1);
			add(Par);
			add(OK);
			setModal(true);
			setTitle("세부설정-파티클명");
			setBounds(200, 150, 200, 150);
			setLayout(null);
			OK.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JButton OK = (JButton) e.getSource();
					if(OK.getText().equals("확인")) {
						Particle = Par.getText();
						setVisible(false);
						new Settings();
					}
				}
				
			});
			setVisible(true);
		}
	}
	
	
	class Settings extends JDialog {

		public Settings() {
			SetN = new JTextField(Name);
			SetN.setBounds(10, 20, 150, 20);
			SetN.setFont(f1);
			SetN.setVisible(true);
			SetN.setEditable(true);
			SetN_O = new JButton("다음");
			SetN_O.setBounds(10, 50, 70, 30);
			SetN_O.setFont(f1);
			add(SetN);
			add(SetN_O);
			setModal(true);
			setTitle("세부설정-실행자");
			setBounds(200, 150, 200, 150);
			setLayout(null);
			SetN_O.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JButton SetN_O = (JButton) e.getSource();
					if(SetN_O.getText().equals("다음")) {
						Name = SetN.getText();
						setVisible(false);
						if (blueberry_babo == true) {
							new Setting_B();
						}
						if (blueberry_babo == false){
							new Setting_FN();
						}
						setVisible(false);
					}
				}
				
			});
			setVisible(true);
		}
	}
	class Setting_Y extends JDialog {
		public Setting_Y() {
			SetFNs = new JTextField(FN);
			SetFNs.setBounds(10, 20, 150, 20);
			SetFNs.setFont(f1);
			SetFNs.setVisible(true);
			SetFNs.setEditable(true);
			SetFNs_O = new JButton("저장하기");
			SetFNs_O.setBounds(10, 50, 70, 30);
			SetFNs_O.setFont(f1);
			add(SetFNs);
			add(SetFNs_O);
			setModal(true);
			setTitle("세부설정-확장자");
			setBounds(200, 150, 200, 150);
			setLayout(null);
			SetFNs_O.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JButton SetNs_O = (JButton) e.getSource();
					if(SetNs_O.getText().equals("저장하기")) {
						FN = SetFNs.getText();
						try {
							new Setting_C();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						setVisible(false);
					}
				}
			});
			setVisible(true);
		}
	}
	
	
	class Setting_B extends JDialog {
		public Setting_B() {
			SetFNs = new JTextField(BN);
			SetFNs.setBounds(10, 20, 150, 20);
			SetFNs.setFont(f1);
			SetFNs.setVisible(true);
			SetFNs.setEditable(true);
			SetFNs_O = new JButton("다음");
			SetFNs_O.setBounds(10, 50, 70, 30);
			SetFNs_O.setFont(f1);
			add(SetFNs);
			add(SetFNs_O);
			setModal(true);
			setTitle("세부설정-행동팩이름");
			setBounds(200, 150, 200, 150);
			setLayout(null);
			SetFNs_O.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JButton SetNs_O = (JButton) e.getSource();
					if(SetNs_O.getText().equals("다음")) {
						BN = SetFNs.getText();
						new Setting_R();
						setVisible(false);
					}
				}
			});
			setVisible(true);
		}
	}
	
	
	class Setting_R extends JDialog {
		public Setting_R() {
			SetFNss = new JTextField(RN);
			SetFNss.setBounds(10, 20, 150, 20);
			SetFNss.setFont(f1);
			SetFNss.setVisible(true);
			SetFNss.setEditable(true);
			SetFNss_O = new JButton("저장.");
			SetFNss_O.setBounds(10, 50, 70, 30);
			SetFNss_O.setFont(f1);
			add(SetFNss);
			add(SetFNss_O);
			setModal(true);
			setTitle("세부설정-리소스팩이름");
			setBounds(200, 150, 200, 150);
			setLayout(null);
			SetFNss_O.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JButton SetNss_O = (JButton) e.getSource();
					if(SetNss_O.getText().equals("저장.")) {
						new Setting_Y();
						RN = SetFNss.getText();
						setVisible(false);
					}
				}
			});
			setVisible(true);
		}
	}
	
	class Setting_FN extends JDialog {
		public Setting_FN() {
			SetFN = new JTextField(FN);
			SetFN.setBounds(10, 20, 150, 20);
			SetFN.setFont(f1);
			SetFN.setVisible(true);
			SetFN.setEditable(true);
			SetFN_O = new JButton("저장");
			SetFN_O.setBounds(10, 50, 70, 30);
			SetFN_O.setFont(f1);
			add(SetFN);
			add(SetFN_O);
			setModal(true);
			setTitle("세부설정-확장자");
			setBounds(200, 150, 200, 150);
			setLayout(null);
			SetFN_O.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JButton SetN_O = (JButton) e.getSource();
					if(SetN_O.getText().equals("저장")) {
						FN = SetFN.getText();
						setVisible(false);
						try {

				            File input = new File("input.png");
				            BufferedImage image = ImageIO.read(input);

				            BufferedImage result = new BufferedImage(
				           	image.getWidth(),
				            image.getHeight(),
				            BufferedImage.TYPE_BYTE_BINARY);

				            Graphics2D graphic = result.createGraphics();
				            graphic.drawImage(image, 0, 0, Color.WHITE, null);
				            graphic.dispose();

				            File output = new File("image_ptp_o.png");
				            ImageIO.write(result, "png", output);

				        }  catch (IOException e1) {
				            e1.printStackTrace();
				        }
				        String imgOriginalPath= "image_ptp_o.png";           // 원본 이미지 파일명
				        String imgTargetPath= "input.png";    // 새 이미지 파일명
				        String imgFormat = "png";                             // 새 이미지 포맷. jpg, gif 등
				        int newWidth = 	50;                                  // 변경 할 넓이
				        int newHeight = 50;                                 // 변경 할 높이
				        String mainPosition = "X";                             // W:넓이중심, H:높이중심, X:설정한 수치로(비율무시)
				 
				        Image image;
				        int imageWidth;
				        int imageHeight;
				        double ratio;
				        int w;
				        int h;
				 
				        try{
				            // 원본 이미지 가져오기
				            image = ImageIO.read(new File(imgOriginalPath));
				 
				            // 원본 이미지 사이즈 가져오기
				            imageWidth = image.getWidth(null);
				            imageHeight = image.getHeight(null);
				 
				            if(mainPosition.equals("W")){    // 넓이기준
				 
				                ratio = (double)newWidth/(double)imageWidth;
				                w = (int)(imageWidth * ratio);
				                h = (int)(imageHeight * ratio);
				 
				            }else if(mainPosition.equals("H")){ // 높이기준
				 
				                ratio = (double)newHeight/(double)imageHeight;
				                w = (int)(imageWidth * ratio);
				                h = (int)(imageHeight * ratio);
				 
				            }else{ //설정값 (비율무시)
				 
				                w = newWidth;
				                h = newHeight;
				            }
				 
				            // 이미지 리사이즈
				            // Image.SCALE_DEFAULT : 기본 이미지 스케일링 알고리즘 사용
				            // Image.SCALE_FAST    : 이미지 부드러움보다 속도 우선
				            // Image.SCALE_REPLICATE : ReplicateScaleFilter 클래스로 구체화 된 이미지 크기 조절 알고리즘
				            // Image.SCALE_SMOOTH  : 속도보다 이미지 부드러움을 우선
				            // Image.SCALE_AREA_AVERAGING  : 평균 알고리즘 사용
				            Image resizeImage = image.getScaledInstance(w, h, Image.SCALE_REPLICATE);
				 
				            // 새 이미지  저장하기
				            BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
				            Graphics g = newImage.getGraphics();
				            g.drawImage(resizeImage, 0, 0, null);
				            g.dispose();
				            ImageIO.write(newImage, imgFormat, new File(imgTargetPath));
				 
				        }catch (Exception e1){
				 
				            e1.printStackTrace();
				 
				        }
				        try {
							new run_py();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
				        File deleteFile1 = new File("input.png");
				        deleteFile1.delete();
						
						
						
						
						
						
						
						BufferedImage image1 = loadImage("image_ptp.png");  

						int x = image1.getWidth(null);  

						int y = image1.getHeight(null);  

						int x_t = x;
						int y_t = y;
						if (x_t >= 0) {
							double s_t = Set_k;
							s_t /= 10.0;
							while (x_t >= 0) {
								
								Color color = new Color(image1.getRGB(x_t-1, y_t-1));   //좌표 선택

								int red = color.getRed();

								int blue = color.getBlue();

								int green = color.getGreen();
								
								if (red == 0) {
									if (blue == 0) {
										if (green == 0) {
											double x_m = x_t;
											double y_m = y_t;
											x_m -= 25;
											y_m -= 25;
											x_m *= s_t;
											y_m *= s_t;
											Set_mcfuntion += "execute "+Name+" ~~~ particle "+Particle+" ^"+x_m+"^"+y_m+"^"+"\n";
										}
									}
								}
								x_t -= 1;
								if (x_t == 0) {
									x_t = x;
									y_t -= 1;
								}
								if (y_t == 0) {
									break;
								}
								
							}
						}
						String fileName = input_k+"\\"+FN;
						File file = new File(fileName);
						try {
							FileWriter fw = new FileWriter(file, false) ;
							fw.write(Set_mcfuntion);
				            fw.flush();
				            fw.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						
						setVisible(false);
						
						
						
					}
				}
				
			});
			setVisible(true);
		}
	}
	
	
	
	
	
	class Setting_C extends JDialog {
		public Setting_C() throws IOException {

	        new run_py();
			
			BufferedImage image2 = loadImage("input.png");  

			int xX = image2.getWidth(null);  

			int yY = image2.getHeight(null);
			
			int YYY = yY;
			int XXX = xX;
			if (xX < 50) {
				if (yY < 50) {
					String imgOriginalPath= "input.png";           // 원본 이미지 파일명
					String imgTargetPath= "image_ptp.png";    // 새 이미지 파일명
					String imgFormat = "png";                             // 새 이미지 포맷. jpg, gif 등
					int newWidth = 	xX;                                  // 변경 할 넓이
					int newHeight = yY;                                 // 변경 할 높이
					String mainPosition = "X";                             // W:넓이중심, H:높이중심, X:설정한 수치로(비율무시)
					Image image;
				int imageWidth;
				int imageHeight;
				double ratio;
				int w;
				int h;
		
				try{
					// 원본 이미지 가져오기
					image = ImageIO.read(new File(imgOriginalPath));
		
					// 원본 이미지 사이즈 가져오기
					imageWidth = image.getWidth(null);
					imageHeight = image.getHeight(null);
		
					if(mainPosition.equals("W")){    // 넓이기준
		
						ratio = (double)newWidth/(double)imageWidth;
						w = (int)(imageWidth * ratio);
						h = (int)(imageHeight * ratio);
		
					}else if(mainPosition.equals("H")){ // 높이기준
		
						ratio = (double)newHeight/(double)imageHeight;
						w = (int)(imageWidth * ratio);
						h = (int)(imageHeight * ratio);
	 
					}else{ //설정값 (비율무시)
	 
						w = newWidth;
						h = newHeight;
					}
	 
					// 이미지 리사이즈
					// Image.SCALE_DEFAULT : 기본 이미지 스케일링 알고리즘 사용
					// Image.SCALE_FAST    : 이미지 부드러움보다 속도 우선
					// Image.SCALE_REPLICATE : ReplicateScaleFilter 클래스로 구체화 된 이미지 크기 조절 알고리즘
					// Image.SCALE_SMOOTH  : 속도보다 이미지 부드러움을 우선
					// Image.SCALE_AREA_AVERAGING  : 평균 알고리즘 사용
					Image resizeImage = image.getScaledInstance(w, h, Image.SCALE_REPLICATE);
	 
					// 새 이미지  저장하기
					BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
					Graphics g = newImage.getGraphics();
					g.drawImage(resizeImage, 0, 0, null);
					g.dispose();
					ImageIO.write(newImage, imgFormat, new File(imgTargetPath));
		
				}catch (Exception e1){
		
					e1.printStackTrace();
		
				}
				}
			}
			if (xX >= 50) {
				String imgOriginalPath= "input.png";           // 원본 이미지 파일명
				String imgTargetPath= "image_ptp.png";    // 새 이미지 파일명
				String imgFormat = "png";                             // 새 이미지 포맷. jpg, gif 등
				int newWidth = 	50;                                  // 변경 할 넓이
				int newHeight = YYY;                                 // 변경 할 높이
				String mainPosition = "X";                             // W:넓이중심, H:높이중심, X:설정한 수치로(비율무시)
				Image image;
				int imageWidth;
				int imageHeight;
				double ratio;
				int w;
				int h;
		
				try{
					// 원본 이미지 가져오기
					image = ImageIO.read(new File(imgOriginalPath));
		
					// 원본 이미지 사이즈 가져오기
					imageWidth = image.getWidth(null);
					imageHeight = image.getHeight(null);
		
					if(mainPosition.equals("W")){    // 넓이기준
		
						ratio = (double)newWidth/(double)imageWidth;
						w = (int)(imageWidth * ratio);
						h = (int)(imageHeight * ratio);
		
					}else if(mainPosition.equals("H")){ // 높이기준
		
						ratio = (double)newHeight/(double)imageHeight;
						w = (int)(imageWidth * ratio);
						h = (int)(imageHeight * ratio);
	 
					}else{ //설정값 (비율무시)
	 
						w = newWidth;
						h = newHeight;
					}
	 
					// 이미지 리사이즈
					// Image.SCALE_DEFAULT : 기본 이미지 스케일링 알고리즘 사용
					// Image.SCALE_FAST    : 이미지 부드러움보다 속도 우선
					// Image.SCALE_REPLICATE : ReplicateScaleFilter 클래스로 구체화 된 이미지 크기 조절 알고리즘
					// Image.SCALE_SMOOTH  : 속도보다 이미지 부드러움을 우선
					// Image.SCALE_AREA_AVERAGING  : 평균 알고리즘 사용
					Image resizeImage = image.getScaledInstance(w, h, Image.SCALE_REPLICATE);
	 
					// 새 이미지  저장하기
					BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
					Graphics g = newImage.getGraphics();
					g.drawImage(resizeImage, 0, 0, null);
					g.dispose();
					ImageIO.write(newImage, imgFormat, new File(imgTargetPath));
		
				}catch (Exception e1){
		
					e1.printStackTrace();
		
				}
			}
			if (yY >= 50) {
				String imgOriginalPath1= "input.png";           // 원본 이미지 파일명
				String imgTargetPath1= "image_ptp.png";    // 새 이미지 파일명
				String imgFormat1 = "png";                             // 새 이미지 포맷. jpg, gif 등
				int newWidth1 = XXX;                                  // 변경 할 넓이
				int newHeight1 = 50;                                 // 변경 할 높이
				String mainPosition1 = "X";                             // W:넓이중심, H:높이중심, X:설정한 수치로(비율무시)
				Image image1;
				int imageWidth1;
				int imageHeight1;
				double ratio1;
				int w1;
				int h1;
		
				try{
					// 원본 이미지 가져오기
					image1 = ImageIO.read(new File(imgOriginalPath1));
		
					// 원본 이미지 사이즈 가져오기
					imageWidth1 = image1.getWidth(null);
					imageHeight1 = image1.getHeight(null);
		
					if(mainPosition1.equals("W")){    // 넓이기준
		
						ratio1 = (double)newWidth1/(double)imageWidth1;
						w1 = (int)(imageWidth1 * ratio1);
						h1 = (int)(imageHeight1 * ratio1);
		
					}else if(mainPosition1.equals("H")){ // 높이기준
		
						ratio1 = (double)newHeight1/(double)imageHeight1;
						w1 = (int)(imageWidth1 * ratio1);
						h1 = (int)(imageHeight1 * ratio1);
	 
					}else{ //설정값 (비율무시)
	 
						w1 = newWidth1;
						h1 = newHeight1;
					}
	 
					// 이미지 리사이즈
					// Image.SCALE_DEFAULT : 기본 이미지 스케일링 알고리즘 사용
					// Image.SCALE_FAST    : 이미지 부드러움보다 속도 우선
					// Image.SCALE_REPLICATE : ReplicateScaleFilter 클래스로 구체화 된 이미지 크기 조절 알고리즘
					// Image.SCALE_SMOOTH  : 속도보다 이미지 부드러움을 우선
					// Image.SCALE_AREA_AVERAGING  : 평균 알고리즘 사용
					Image resizeImage = image1.getScaledInstance(w1, h1, Image.SCALE_REPLICATE);
	 
					// 새 이미지  저장하기
					BufferedImage newImage = new BufferedImage(w1, h1, BufferedImage.TYPE_INT_RGB);
					Graphics g = newImage.getGraphics();
					g.drawImage(resizeImage, 0, 0, null);
					g.dispose();
					ImageIO.write(newImage, imgFormat1, new File(imgTargetPath1));
		
				}catch (Exception e1){
		
					e1.printStackTrace();
		
				}
			}
	        File deleteFile1 = new File("input.png");
	        deleteFile1.delete();
			File setfile = new File(input_k+"\\addon\\res\\textures\\particle");
			setfile.mkdirs();
			File setfiles = new File(input_k+"\\addon\\res\\particles");
			setfiles.mkdirs();
			File setbhv = new File(input_k+"\\addon\\beh\\functions");
			setbhv.mkdirs();
			String uuid = UUID.randomUUID().toString();
			String uuid2 = UUID.randomUUID().toString();
			String uuid3 = UUID.randomUUID().toString();
			String uuid4 = UUID.randomUUID().toString();
			String txts = "{\r\n"
					+ "    \"format_version\": 2,\r\n"
					+ "    \"header\": {\r\n"
					+ "        \"description\": \"made with magic\",\r\n"
					+ "        \"name\": \""+RN+"\",\r\n"
					+ "        \"uuid\": \""+uuid+"\",\r\n"
					+ "        \"version\": [0, 0, 1],\r\n"
					+ "        \"min_engine_version\": [ 1, 14, 0 ]\r\n"
					+ "    },\r\n"
					+ "    \"modules\": [\r\n"
					+ "        {\r\n"
					+ "            \"description\": \"made with magic\",\r\n"
					+ "            \"type\": \"resources\",\r\n"
					+ "            \"uuid\": \""+uuid2+"\",\r\n"
					+ "            \"version\": [0, 0, 1]\r\n"
					+ "        }\r\n"
					+ "    ]\r\n"
					+ "}";
	         
	        String fileName = input_k+"\\addon\\res\\manifest.json" ;
	        String bhvname = input_k+"\\addon\\beh\\manifest.json";
	        String text = "{\r\n"
	        		+ "    \"format_version\": 2,\r\n"
	        		+ "    \"header\": {\r\n"
	        		+ "        \"description\": \"made with magic\",\r\n"
	        		+ "        \"name\": \""+BN+"\",\r\n"
	        		+ "        \"uuid\": \""+uuid3+"\",\r\n"
	        		+ "        \"version\": [1, 0, 0],\r\n"
	        		+ "        \"min_engine_version\": [1, 13, 0]\r\n"
	        		+ "    },\r\n"
	        		+ "    \"modules\": [\r\n"
	        		+ "        {\r\n"
	        		+ "            \"type\": \"data\",\r\n"
	        		+ "            \"uuid\": \""+uuid4+"\",\r\n"
	        		+ "            \"version\": [1, 0, 0]\r\n"
	        		+ "        }\r\n"
	        		+ "    ]\r\n"
	        		+ "}";
	        
	        try{
	             
	            // 파일 객체 생성
	            File filesz = new File(bhvname) ;
	             
	            // true 지정시 파일의 기존 내용에 이어서 작성
	            FileWriter fw = new FileWriter(filesz, false) ;
	             
	            // 파일안에 문자열 쓰기
	            fw.write(text);
	            fw.flush();
	 
	            // 객체 닫기
	            fw.close();
	             
	             
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        
	        try{
	             
	            // 파일 객체 생성
	            File file = new File(fileName) ;
	             
	            // true 지정시 파일의 기존 내용에 이어서 작성
	            FileWriter fw = new FileWriter(file, false) ;
	             
	            // 파일안에 문자열 쓰기
	            fw.write(txts);
	            fw.flush();
	 
	            // 객체 닫기
	            fw.close();
	             
	             
	        }catch(Exception e){
	            e.printStackTrace();
	        }
			
			
			
			BufferedImage image1 = loadImage("image_ptp.png");  
			int xs = image1.getWidth(null);

			int ys = image1.getHeight(null);

			int y_ts = ys;
			int x_ts = xs;
			
				
			if (x_ts >= 0) {
				double s_ts = Set_k;
				s_ts /= 10.0;
				while (x_ts >= 0) {
					
					Color color = new Color(image1.getRGB(x_ts-1, y_ts-1));   //좌표 선택

					int red = color.getRed();

					int blue = color.getBlue();

					int green = color.getGreen();
					
					BufferedImage img = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
				    Graphics2D graphics = img.createGraphics();    // Graphics2D를 얻어와 그림을 그린다
				    
				   
				    graphics.setColor(color);                       // 색상을 지정한다(파란색)
				    graphics.fillRect(0,0,10,10);                          // 사각형을 하나 그린다
				    
				    try{
				         File file = new File(input_k+"\\addon\\res\\textures\\particle\\RBG_"+red+"_"+blue+"_"+green+".png");        // 파일의 이름을 설정한다
				         ImageIO.write(img, "png", file);                     // write메소드를 이용해 파일을 만든다
				    }
				    

				    catch(Exception e){e.printStackTrace();}
				    
				    String txt = "{\r\n"
				    		+ "  \"format_version\": \"1.10.0\",\r\n"
				    		+ "  \"particle_effect\": {\r\n"
				    		+ "    \"description\": {\r\n"
				    		+ "      \"identifier\": \"magic:RBG_"+red+"_"+blue+"_"+green+"\",\r\n"
				    		+ "      \"basic_render_parameters\": {\r\n"
				    		+ "        \"material\": \"particles_blend\",\r\n"
				    		+ "        \"texture\": \""+"textures/particle/RBG_"+red+"_"+blue+"_"+green+"\"\r\n"
				    		+ "      }\r\n"
				    		+ "    },\r\n"
				    		+ "    \"components\": {\r\n"
				    		+ "      \"minecraft:emitter_rate_instant\": {\r\n"
				    		+ "        \"num_particles\": 1\r\n"
				    		+ "      },        \r\n"
				    		+ "      \"minecraft:emitter_lifetime_once\": {\r\n"
				    		+ "      },\r\n"
				    		+ "      \"minecraft:emitter_shape_point\": {\r\n"
				    		+ "        \"direction\": [ \"variable.direction.x\", \"variable.direction.y\", \"variable.direction.z\" ]\r\n"
				    		+ "      },\r\n"
				    		+ "      \"minecraft:particle_initial_speed\": 0.0,\r\n"
				    		+ "      \"minecraft:particle_lifetime_expression\": {\r\n"
				    		+ "        \"max_lifetime\": \"Math.Random(0.75, 0.9)\"\r\n"
				    		+ "      },\r\n"
				    		+ "      \"minecraft:particle_motion_dynamic\": {\r\n"
				    		+ "        \"linear_acceleration\": [ 0.1, 0.1, 0.1 ],\r\n"
				    		+ "        \"linear_drag_coefficient\": 0.49\r\n"
				    		+ "      },\r\n"
				    		+ "      \"minecraft:particle_appearance_billboard\": {\r\n"
				    		+ "        \"size\": [ \"(variable.particle_random_1 * 0.5f + 0.5f) * 0.2\", \"(variable.particle_random_1 * 0.5f + 0.5f) * 0.2\" ],\r\n"
				    		+ "        \"facing_camera_mode\": \"lookat_xyz\",\r\n"
				    		+ "        \"uv\": {\r\n"
				    		+ "          \"texture_width\": 10,\r\n"
				    		+ "          \"texture_height\": 10,\r\n"
				    		+ "          \"flipbook\": {\r\n"
				    		+ "            \"base_UV\": [ 1, 1 ],\r\n"
				    		+ "            \"size_UV\": [ 8, 8 ],\r\n"
				    		+ "            \"step_UV\": [ -8, 0 ],\r\n"
				    		+ "            \"frames_per_second\": 8,\r\n"
				    		+ "            \"max_frame\": 8,\r\n"
				    		+ "            \"stretch_to_lifetime\": true,\r\n"
				    		+ "            \"loop\": false\r\n"
				    		+ "          }\r\n"
				    		+ "        }\r\n"
				    		+ "      },\r\n"
				    		+ "      \"minecraft:particle_appearance_tinting\": {\r\n"
				    		+ "        \"color\": [ \"variable.particle_age > (variable.particle_lifetime / 2.0) ? 1 - (0.0153 * (1 - Math.pow(0.7, variable.particle_age)) / (1 - 0.7)) : 1.0\", \"variable.particle_age > (variable.particle_lifetime / 2.0) ? 1 - (0.0387 * (1 - Math.pow(0.7, variable.particle_age)) / (1 - 0.7)) : 1.0\", \"variable.particle_age > (variable.particle_lifetime / 2.0) ? 1 - (0.0636 * (1 - Math.pow(0.7, variable.particle_age)) / (1 - 0.7)) : 1.0\", \"variable.particle_age > (variable.particle_lifetime / 2.0) ? 1 - 0.60 * ((variable.particle_age - (variable.particle_lifetime / 2.0)) / (variable.particle_lifetime / 2.0)) : 1.0\" ]\r\n"
				    		+ "      },\r\n"
				    		+ "      \"minecraft:particle_appearance_lighting\": {}\r\n"
				    		+ "    }\r\n"
				    		+ "  }\r\n"
				    		+ "}" ;
			         
			        String fileNames = input_k+"\\addon\\res\\particles\\RBG_"+red+"_"+blue+"_"+green+".json";
			         
			         
			        try{
			             
			            // 파일 객체 생성
			            File file = new File(fileNames) ;
			             
			            // true 지정시 파일의 기존 내용에 이어서 작성
			            FileWriter fw = new FileWriter(file, false) ;
			             
			            // 파일안에 문자열 쓰기
			            fw.write(txt);
			            fw.flush();
			 
			            // 객체 닫기
			            fw.close();
			             
			             
			        }catch(Exception e){
			            e.printStackTrace();
			        }
			        double x_ms = x_ts;
					double y_ms = y_ts;
					y_ms /= 2;
					x_ms /= 2;
					double yss = y_ms;
					double xss = x_ms;
					y_ms = y_ts;
					x_ms = x_ts;
					y_ms -= yss;
					x_ms -= xss;
					y_ms *= s_ts;
					x_ms *= s_ts;
					Set_mcfuntion += "execute "+Name+" ~~~ particle "+"magic:RBG_"+red+"_"+blue+"_"+green+" ^"+x_ms+"^"+y_ms+"^"+"\n";
					x_ts -= 1;
					if (x_ts == 0) {
						x_ts = xs;
						y_ts -= 1;
					}
					if (y_ts == 0) {
						break;
					}
					
				}
			}
			
			String fileName2 = input_k+"\\addon\\beh\\functions\\"+FN;
			File file2s = new File(fileName2);
			try {
				FileWriter fw = new FileWriter(file2s, false) ;
				fw.write(Set_mcfuntion);
	            fw.flush();
	            fw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			new Main();
		}
	}
	class Main { 
		public Main() { 
	
			// 압축 파일 위치와 압축된 파일 
			String zipPath = input_k+"\\addon\\";
			String zipFile = "addons.mcaddon";
			zipFile = zipFile.replace("\\", "/");
			zipPath = zipPath.replace("\\", "/");
			CompressZip compressZip = new CompressZip();
			try { 
				if (!compressZip.compress(zipPath, zipPath, zipFile)) {
				} 
			} catch (Throwable e) { e.printStackTrace(); }
			String addr = "https://github.com/Magic3910/PTP_project-Magic3910";
			Process process = null;
			String[] cmd = new String[] {"rundll32", "url.dll", "FileProtocolHandler", addr};
			String str = null;

			try {

			    // 프로세스 빌더를 통하여 외부 프로그램 실행
			    process = new ProcessBuilder(cmd).start();

			    // 외부 프로그램의 표준출력 상태 버퍼에 저장
			    BufferedReader stdOut = new BufferedReader( new InputStreamReader(process.getInputStream()) );
			   
			    // 표준출력 상태를 출력
			    while( (str = stdOut.readLine()) != null ) {
			        System.out.println(str);
			    }
			   
			} catch (IOException e) {
			    e.printStackTrace();

			}

		}
		
	}
	class run_py {
		public run_py() throws IOException {
			try {

	            URL u = new URL("https://drive.google.com/uc?export=download&id=1iNhcZiJ9x7_AzVaBf9n91yBMcwbd9kJl");

	            File filePath = new File("./");

				filePath.mkdirs();

				FileOutputStream fos = new FileOutputStream("./flip.exe");

				InputStream is = u.openStream();

				byte[] buf = new byte[1024];

				double len = 0;

				double tmp = 0;

				double percent = 0;

				while ((len = is.read(buf)) > 0) {

					tmp += len / 1024 / 1024;

					percent = tmp / 1229 * 100;

					System.out.printf("%.2f", tmp);

					System.out.print("MB / 1229MB (진행률: ");

					System.out.printf("%.1f", percent);

					System.out.println("%)");

					fos.write(buf, 0, (int) len);

				}

				fos.close();

				is.close();

	        } catch (Exception e) {

	            System.out.println("다운로드 오류입니다. 나중에 다시 받아보세요.");

	        }
			Runtime rt = Runtime.getRuntime();
			Process py = rt.exec("flip.exe");
			try {
				py.waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        File deleteF = new File("flip.exe");
	        deleteF.delete();
		}
	}


	
}
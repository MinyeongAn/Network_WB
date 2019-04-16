package NetworkProject;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

public class busUI extends JFrame implements ActionListener, ListSelectionListener {
	JButton station_bt;
	JTextField station_tf;
	static JList<String> station_list;
	static JList<String> station_id_list;
	static JList<String> bus_list;
	static JScrollPane scroll;
	ArrayList<data> station_name = new ArrayList<data>();
	boolean check1 = true;
	DefaultListModel model;

	JButton sendbt;
	JTextField tf;
	JTextArea ta;
	JLabel serch, tclb, tmaxlb, tminlb, statlb, dustlb, skylb, locationlb;
	JLabel tcdata, tmaxdata, tmindata, statdata, duststatdata, locationdata;
	JLabel skystat;
	ImageIcon i;
	Font f1;
	String weathernum = "";
	Object tc, tmax, tmin, duststat, dustvalue, skyname, skycode;

	public busUI() {
		setSize(680, 220);
		setLocation(400, 400);
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("출근길 도우미");
		f1 = new Font("굴림", Font.PLAIN, 12);

		station_tf = new JTextField();
		station_tf.setSize(255, 20);
		station_tf.setLocation(300, 15);
		station_tf.setFont(f1);

		station_bt = new JButton("정류장 검색");
		station_bt.setSize(100, 20);
		station_bt.setLocation(560, 15);
		station_bt.setFont(f1);

		station_list = new JList(new DefaultListModel());
		scroll = new JScrollPane(station_list);
		scroll.setSize(360, 120);
		scroll.setLocation(300, 50);
		scroll.setFont(f1);

		station_bt.addActionListener(new ButtonEvent1());
		station_list.addListSelectionListener(this);

		add(station_bt);
		add(station_tf);
		add(scroll);

		serch = new JLabel("지역 : ");
		serch.setSize(40, 20);
		serch.setLocation(10, 15);
		serch.setFont(f1);

		tf = new JTextField();
		tf.setSize(160, 20);
		tf.setLocation(50, 15);

		sendbt = new JButton("검색");
		sendbt.setSize(75, 20);
		sendbt.setLocation(215, 15);
		sendbt.setFont(f1);

		locationlb = new JLabel("지역 : ");
		locationlb.setSize(80, 20);;
		//locationlb.setLocation(39, 45);
		locationlb.setLocation(39, 45);
		locationlb.setFont(f1);

		tclb = new JLabel("현  재   기  온 : ");
		tclb.setSize(120, 20);;
		tclb.setLocation(10, 70);
		tclb.setFont(f1);

		tmaxlb = new JLabel("최  고   기  온 : ");
		tmaxlb.setSize(120, 20);;
		tmaxlb.setLocation(10, 90);
		tmaxlb.setFont(f1);

		tminlb = new JLabel("최  저   기  온 : ");
		tminlb.setSize(120, 20);;
		tminlb.setLocation(10, 110);
		tminlb.setFont(f1);

		statlb = new JLabel("미세먼지 상태 : ");
		statlb.setSize(120, 20);;
		statlb.setLocation(10, 130);
		statlb.setFont(f1);

		dustlb = new JLabel("미세먼지 농도 : ");
		dustlb.setSize(120, 20);;
		dustlb.setLocation(10, 150);
		dustlb.setFont(f1);

		skylb = new JLabel("");
		skylb.setSize(120, 20);;
		skylb.setLocation(175, 60);
		skylb.setFont(f1);

		locationdata = new JLabel("");
		locationdata.setSize(200, 20);;
		locationdata.setLocation(80, 45);
		locationdata.setFont(f1);

		tcdata = new JLabel("");
		tcdata.setSize(50, 20);;
		tcdata.setLocation(100, 70);
		tcdata.setFont(f1);

		tmaxdata = new JLabel("");
		tmaxdata.setSize(50, 20);;
		tmaxdata.setLocation(100, 90);
		tmaxdata.setFont(f1);

		tmindata = new JLabel("");
		tmindata.setSize(50, 20);;
		tmindata.setLocation(100, 110);
		tmindata.setFont(f1);

		statdata = new JLabel("");
		statdata.setSize(70, 20);;
		statdata.setLocation(100, 130);
		statdata.setFont(f1);

		duststatdata = new JLabel("");
		duststatdata.setSize(50, 20);;
		duststatdata.setLocation(100, 150);
		duststatdata.setFont(f1);
		

		sendbt.addActionListener(new ButtonEvent2());

		i = new ImageIcon("weather_icons/"+weathernum+".png");
		skystat = new JLabel(i);
		skystat.setSize(100,100);
		skystat.setLocation(175, 65);

		add(serch);
		add(tf);
		add(sendbt);

		add(locationlb);
		add(tclb);
		add(tmaxlb);
		add(tminlb);
		add(statlb);
		add(dustlb);
		add(skylb);

		add(locationdata);
		add(tcdata);
		add(tmaxdata);
		add(tmindata);
		add(statdata);
		add(duststatdata);

		add(skystat);

		setVisible(true);
	}
	public class ButtonEvent1 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < station_name.size(); i++)
				model.remove(0);

			if(e.getSource() == station_bt){
				model = (DefaultListModel) station_list.getModel();
				try {
					station_name = stationname.stationnames(station_tf.getText());
					if(station_name.size() == 0 ){
						JOptionPane.showMessageDialog(null,"해당하는 정류장이 없습니다");
					}

					for (int i = 0; i < station_name.size(); i++)
						model.addElement(station_name.get(i).getName());
					check1 = true;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
	}

	public class ButtonEvent2 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(tf.getText().equals("")){
				JOptionPane.showMessageDialog(null,"지역을 입력하세요");
			}
			else{
				locationdata.setText(tf.getText());

				Object lat = "37.6";
				Object lon = "127";
				String location = "";      
				byte[] b;

				location = tf.getText();

				try {
					b = location.getBytes("UTF-8");
					location = "";
					for(int i = 0 ; i < b.length ; i++)	{
						if(Integer.toHexString(b[i]).length()>3){
							location += "%" + Integer.toHexString(b[i]).substring(6,8);
						}
					}

					URL locationurl = new URL("http://maps.googleapis.com/maps/api/geocode/json?sensor=false&language=ko&address="+location);
					InputStreamReader locationisr = new InputStreamReader(locationurl.openConnection().getInputStream(), "UTF-8");

					// 지역부분
					JSONObject locationobject = (JSONObject)JSONValue.parse(locationisr);
					String locationdata = locationobject.toString();

					JSONParser jsonParser = new JSONParser();
					JSONObject jsonObj = (JSONObject) jsonParser.parse(locationdata);
					JSONArray lmemberArray = (JSONArray) jsonObj.get("results");

					for(int i=0 ; i<lmemberArray.size() ; i++){
						JSONObject locationtempObj = (JSONObject) lmemberArray.get(i);

						JSONObject geometrydata = (JSONObject) locationtempObj.get("geometry");
						JSONObject lonlatdata = (JSONObject) geometrydata.get("location");   

						lat = lonlatdata.get("lat");
						lon = lonlatdata.get("lng");
					}


					URL weatherurl = new URL("http://apis.skplanetx.com/weather/current/hourly?"+
							"version=1"+
							"&lat="+lat+
							"&lon="+lon+
							"&appKey=bb51aee1-9ee7-3460-97d6-1fb1d2254ae0");
					URL dusturl = new URL("http://apis.skplanetx.com/weather/dust?"+
							"&lon="+lon+
							"&lat="+lat+
							"&version=1&appKey=bb51aee1-9ee7-3460-97d6-1fb1d2254ae0");

					InputStreamReader wisr = new InputStreamReader(weatherurl.openConnection().getInputStream(), "UTF-8");
					InputStreamReader dustisr = new InputStreamReader(dusturl.openConnection().getInputStream(), "UTF-8");

					// 날씨부분
					JSONObject weatherobject = (JSONObject)JSONValue.parse(wisr);
					JSONObject weatherhead = (JSONObject) weatherobject.get("weather");
					String weatherdata = weatherhead.toString();
					JSONObject dustobject = (JSONObject)JSONValue.parse(dustisr);
					JSONObject dusthead = (JSONObject) dustobject.get("weather");
					String duststring = dusthead.toString();

					JSONParser wjsonParser = new JSONParser();
					JSONObject wjsonObj = (JSONObject) wjsonParser.parse(weatherdata);
					JSONArray wmemberArray = (JSONArray) wjsonObj.get("hourly");
					JSONParser dustjsonParser = new JSONParser();
					JSONObject dustjsonObj = (JSONObject) dustjsonParser.parse(duststring);
					JSONArray dustmemberArray = (JSONArray) dustjsonObj.get("dust");

					for(int i=0 ; i<wmemberArray.size() ; i++){
						JSONObject tempObj = (JSONObject) wmemberArray.get(i);

						JSONObject griddata = (JSONObject) tempObj.get("sky");
						skyname = griddata.get("name");
						skycode = griddata.get("code");

						skylb.setText(skyname.toString());
						weathernum = skycode.toString();
						ImageIcon test = new ImageIcon("weather_icons/"+weathernum+".png");
						skystat.setIcon(test);

						JSONObject tempdata = (JSONObject) tempObj.get("temperature");
						tc = tempdata.get("tc");
						tmax = tempdata.get("tmax");
						tmin = tempdata.get("tmin");

						tcdata.setText(tc.toString()+"℃");
						tmaxdata.setText(tmax.toString()+"℃");
						tmindata.setText(tmin.toString()+"℃");
					}
					for(int i=0 ; i<dustmemberArray.size() ; i++){
						JSONObject dusttempObj = (JSONObject) dustmemberArray.get(i);

						JSONObject dustdata = (JSONObject) dusttempObj.get("pm10");

						duststat = dustdata.get("grade");
						dustvalue = dustdata.get("value");

						statdata.setText(duststat.toString());
						duststatdata.setText(dustvalue.toString());
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}      

		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == station_list){
			int a = station_list.getAnchorSelectionIndex();
			if(check1)
				new buslistUI(station_name.get(a).getId());
			check1 = false;
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
	public static void main(String[] args){
		new busUI();
	}
}

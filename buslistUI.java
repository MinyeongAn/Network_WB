package NetworkProject;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class buslistUI extends JFrame {
	static JList<String> bus_list;
	ArrayList<data2> bus_num = new ArrayList<data2>();
	static JScrollPane scroll;
	DefaultListModel model,model2,model3,model4,model5;
	boolean ck = true;


	public buslistUI(String id) {
		ck = true;
		setTitle("���� ����Ʈ");
		setSize(600, 750);
		setLocation(400, 400);
		setLayout(null);
		bus_list = new JList(new DefaultListModel());
		model = (DefaultListModel) bus_list.getModel();
		model2 = (DefaultListModel) bus_list.getModel();
		model3 = (DefaultListModel) bus_list.getModel();
		model4 = (DefaultListModel) bus_list.getModel();
		model5 = (DefaultListModel) bus_list.getModel();
		try {
			bus_num = bus.bus_s(id);
			if(bus_num.size() == 0)	{
				ck = false;
				JOptionPane.showMessageDialog(null,"������ �����ϴ� �ٽ� �˻� �� �ּ���");
			}
			else{
				for(int i = 0; i < bus_num.size(); i++){
					model.addElement(bus_num.get(i).getNum()+"��"); 
					model2.addElement("ù��° �����ð�: " + bus_num.get(i).getClock1()+"��");
					model3.addElement("�ι�° �����ð�: " +bus_num.get(i).getClock2()+"��");
					model4.addElement("-----------------------------------------");
				}	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		scroll = new JScrollPane(bus_list);
		scroll.setSize(560, 680);
		scroll.setLocation(10, 10);

		add(scroll);

		setVisible(ck);		
	}
}

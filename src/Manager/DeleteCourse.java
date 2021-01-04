package Manager;

import dbOperaction.AdminOper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public  class DeleteCourse extends JDialog implements ActionListener{
	   JLabel Namelabel;
	   JTextField idtext;
	   JButton Delbt;
	   static  String name;
	   public DeleteCourse(String admin) {
	   	    name=admin;
	   	this.setTitle("管理员删除课程");
	   this.setSize(650,350);
	   this.setLocation(100, 20);
		   this.setLayout(null);
			Namelabel=new JLabel("请输入删除课程编号：");
			Namelabel.setSize(150,50);
			Namelabel.setLocation(50, 0);
			this.add(Namelabel);

			idtext=new JTextField();
			idtext.setSize(160,40);
			idtext.setLocation(200, 0);
			this.add(idtext);

			Delbt=new JButton("确认删除");
			Delbt.setSize(90,38);
			Delbt.setLocation(470, 0);
			this.add(Delbt);
			Delbt.addActionListener(this);
			this.setVisible(true);
	   }
			@Override
			public void actionPerformed(ActionEvent e) {
				String delid = idtext.getText();
				AdminOper adminOper=new AdminOper();
				try {
					Boolean result=adminOper.Admindel(name,delid);
					if(result){
						JOptionPane.showMessageDialog(null, "课程删除成功");
					}
					else{
						JOptionPane.showMessageDialog(null, "课程删除失败");
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				}

			}
//    测试使用
//	public static void main(String[] args) {
//		DeleteCourse a=new DeleteCourse("admin");
//	}
}
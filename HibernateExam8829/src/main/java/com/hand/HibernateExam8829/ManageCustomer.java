package com.hand.HibernateExam8829;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hand.HibernateExam8829.Customer;

public class ManageCustomer
{

	private static SessionFactory factory;
	public static void main(String[] args) {
	try{
		factory = new Configuration().configure().buildSessionFactory();
		}catch (Throwable ex) {
		System.err.println("Failed to create sessionFactory object." + ex);
		throw new ExceptionInInitializerError(ex);
		}
	    //接收输入信息
	    byte store_id=1;
	    System.out.println("store_id:"+store_id);
		Scanner input=new Scanner(System.in);
		System.out.println("firsrt_name:");
		String fname=input.next();
		System.out.println("last_name:");
		String lname=input.next();
		System.out.println("email:");
		String cemail=input.next();
		System.out.println("address_id:");
		short addid=input.nextShort();
		 SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//获取系统时间
	      String ctime=matter.format(new Date());
		System.out.println("create_date:"+ctime);
		
		ManageCustomer ME = new ManageCustomer();
		Integer empID1 = ME.addCustomer(store_id,fname, lname,cemail,addid,ctime);
		
		System.out.println("以保存的信息如下:");
		System.out.println("(store_id)ID:"+store_id);
		System.out.println("FirstName:"+fname);
		System.out.println("LastName:"+lname);
		System.out.println("Email:"+cemail);
		System.out.println("Address_id:"+addid);
		
		System.out.println("请输入要删除的  Customer 的 ID:");
		int deid=input.nextInt();
		System.out.println("你输入的 ID 为  "+deid+"的  Customer 已经删除");
		//ME.deleteCustomersEntity(deid);
		}
	
		public Integer addCustomer(byte store_id, String first_name, String last_name,String email,short address_id,String create_date){
		Session session = factory.openSession();
		Transaction tx = null;
		Integer customersID = null;
		try{
		tx = session.beginTransaction();
		Customer customer = new Customer(store_id,first_name,last_name,email,address_id,create_date);
		customersID = (Integer) session.save(customer);
		tx.commit();
		}catch (HibernateException e) {
		if (tx!=null) tx.rollback();
		e.printStackTrace();
		}finally {
		session.close();
		}
		return customersID;
		}
	
//		public void deleteCustomersEntity(int deid ){
//		Session session = factory.openSession();
//		Transaction tx = null;
//		try{
//		tx = session.beginTransaction();
//		String sql = "DELETE FROM customer WHERE customer_id="+deid;
//		SQLQuery query = session.createSQLQuery(sql);
//		query.addEntity(Customer.class);
//		List customers = query.list();
//		tx.commit();
//		System.out.println("你输入的 ID 为  "+deid+"的  Customer 已经删除");
//		}catch (HibernateException e) {
//		if (tx!=null) tx.rollback();
//		e.printStackTrace();
//		}finally {
//			session.close();
//			}
//		}

}

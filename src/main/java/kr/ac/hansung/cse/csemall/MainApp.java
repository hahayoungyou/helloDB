package kr.ac.hansung.cse.csemall;


import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
	ClassPathXmlApplicationContext context=
			new ClassPathXmlApplicationContext("kr/ac/hansung/cse/conf/beans.xml");

	OfferDao offerDao=(OfferDao) context.getBean("offerDao");
	
	System.out.println("record count is "+offerDao.getRowCount());
	//test cRud method
	List<Offer> offerList=offerDao.getOffers();
	for(Offer offer:offerList)
		System.out.println(offer);//@ToString
	
	//insert
Offer offer=new Offer();
offer.setName("trudy");
offer.setEmail("trudy@hansung.ac.kr");
offer.setText("an instructor of spring framework and i want to sleep");
if(offerDao.insert(offer))
	System.out.println("object is inserted successfully");
else
	System.out.println("object insert failed");

offer=offerDao.getOffer("trudy");
offer.setName("nykim");
if(offerDao.update(offer)) {
	System.out.println("object update failed");
}
else
	System.out.println("object update failed");


//delete
offer=offerDao.getOffer("nykim");
System.out.println(offer);

if(offerDao.delete(offer.getId())) {
	System.out.println("object is deleted successfully");
}
else
	System.out.println("object delete failed");

	context.close();
	
	}
	
	

}

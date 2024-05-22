//package com.shopping.service.util;
//
////import org.hibernate.HibernateException;
//import org.hibernate.cfg.Configuration;
//
//import com.shopping.model.Address;
//import com.shopping.model.Cart;
//import com.shopping.model.CartItem;
//import com.shopping.model.Category;
//import com.shopping.model.OrderItem;
//import com.shopping.model.Orders;
//import com.shopping.model.PaymentTransaction;
//import com.shopping.model.Product;
//import com.shopping.model.ProductMeta;
//import com.shopping.model.ProductReview;
//import com.shopping.model.Tag;
//import com.shopping.model.User;
//import com.shopping.model.UserToken;
//
//public class ApplicationUtil {
//	
////	static Configuration config;
////	
////	public static Configuration configAnnotatedClass() throws HibernateException {
////	 config = new Configuration().configure().addAnnotatedClass(ProductMeta.class).addAnnotatedClass(Product.class).addAnnotatedClass(ProductReview.class).addAnnotatedClass(User.class).addAnnotatedClass(Cart.class).addAnnotatedClass(CartItem.class).addAnnotatedClass(Orders.class).addAnnotatedClass(OrderItem.class).addAnnotatedClass(Tag.class).addAnnotatedClass(PaymentTransaction.class).addAnnotatedClass(Category.class).addAnnotatedClass(Address.class).addAnnotatedClass(UserToken.class);
////	 return config;
////	}
//}

package com.ctbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

	private static final SessionFactory SESSION_FACTORY;

	static {
		StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
		standardServiceRegistryBuilder
				// 連線設定
				.applySetting("hibernate.connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver")
				.applySetting("hibernate.connection.url", "jdbc:sqlserver://localhost:1433;DatabaseName=DB02")
				.applySetting("hibernate.connection.username", "sa")
				.applySetting("hibernate.connection.password", "sa123456")
				// SQL方言,以下使用SQLServer
				.applySetting("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect")
				// 是否要顯示實際操作資料庫時的SQL
				.applySetting("hibernate.show_sql", true)
				// 交易管理類型, 以下使用JDBC Transaction
				.applySetting("hibernate.transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory")
				// Enable Hibernate's automatic session context management
				.applySetting("hibernate.current_session_context_class", "thread");
		StandardServiceRegistry serviceRegistry = standardServiceRegistryBuilder.build();

		// new MetadataSources(new StandardServiceRegistryBuilder().configure().build())
		MetadataSources metadataSources = new MetadataSources(serviceRegistry);
		// Java物件與資料庫表格的映射檔
		metadataSources.addAnnotatedClass(com.ctbc.model.vo.PcmsFlowMainVO.class);
		metadataSources.addAnnotatedClass(com.ctbc.model.vo.PcmsFlowBranchVO.class);
		MetadataBuilder metadataBuilder = metadataSources.getMetadataBuilder();
		SESSION_FACTORY = metadataBuilder.build().buildSessionFactory();
	}

	public static SessionFactory getSessionFactory() {
		return SESSION_FACTORY;
	}

}

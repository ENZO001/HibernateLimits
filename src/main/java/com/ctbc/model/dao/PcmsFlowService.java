package com.ctbc.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.ctbc.model.vo.PcmsFlowMainVO;
import com.ctbc.util.HibernateUtil;

public class PcmsFlowService {

	@Transactional
	public void insertCase(PcmsFlowMainVO pcmsFlowMainVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(pcmsFlowMainVO);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		}
	}
	
	@Transactional
	@SuppressWarnings({"deprecation", "rawtypes"})
	public void getFlowMainBySeqs(List<Integer> seqList) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			// ---------------- 測試一次塞3000個parameter (start) ---------------- 
			NativeQuery query = session.createSQLQuery("SELECT * FROM PCMS_FLOW_MAIN WHERE SEQ IN (:SEQ_LIST)");
			query.setParameter("SEQ_LIST", seqList).addEntity(PcmsFlowMainVO.class);
			List list = query.list();
			System.out.println(list.toString());
			// ---------------- 測試一次塞3000個parameter (end) ----------------
			
			// ---------------- 測試在同個session中，分三次，各1000個parameter(start) ----------------
//			for (int i = 0; i < 3; i++) {
//				List<Integer> testSeqList = new ArrayList<Integer>();
//				for (int j = 1 + 1000*i; j <= (i+1)*1000; j++) {
//					testSeqList.add(j);
//				}
//				System.out.println("testSeqList >>>>>>>> " + testSeqList.toString());
//				NativeQuery query = session.createSQLQuery("SELECT * FROM PCMS_FLOW_MAIN WHERE SEQ IN (:SEQ_LIST)");
//				query.setParameter("SEQ_LIST", testSeqList).addEntity(PcmsFlowMainVO.class);
//				List list = query.list();
//				System.out.println(list.size());
//			}
			// ---------------- 測試在同個session中，分三次，各1000個parameter(end) ----------------
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.getSessionFactory().close();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		PcmsFlowService flowService = new PcmsFlowService();
		
		List<Integer> seqList = new ArrayList<Integer>();
		for (int i = 1; i <= 3000; i++) {
			// ------------ 新增3000筆假資料(start) ------------
//			PcmsFlowMainVO pcmsFlowMainVO = new PcmsFlowMainVO();
//			String caseNo = "0901-1080212-001-000" + i;
//			pcmsFlowMainVO.setCaseNo(caseNo);
//			pcmsFlowMainVO.setCreateUser("Z00040150");
//			pcmsFlowMainVO.setModifyUser("Z00040150");
//			flowService.insertCase(pcmsFlowMainVO);
			// ------------ 新增3000筆假資料(end) ------------
			
			// ---------------- 測試一次塞3000個parameter (start) ----------------  
			seqList.add(i);
			// ---------------- 測試一次塞3000個parameter (end) ----------------
		}
		
		System.out.println("=================");
		// ---------------- 測試一次塞3000個parameter (end) ----------------
		flowService.getFlowMainBySeqs(seqList);
		// ---------------- 測試一次塞3000個parameter (end) ----------------
		System.out.println("=================");
	}
}



























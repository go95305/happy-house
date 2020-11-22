package com.ssafy.happyhouse.service;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.MemberDAO;
import com.ssafy.happyhouse.model.MemberDto;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	SqlSession sqlSession;

	@Override
	public MemberDto login(Map<String, Object> map) throws Exception {
//		System.out.println(map.get("id") + " : " + map.get("pw"));
		if(map.get("user") == null)
			return null;
		MemberDto mem=sqlSession.getMapper(MemberDAO.class).login(map);
		System.out.println(mem);
//		return sqlSession.getMapper(MemberDAO.class).login(map);
		return mem;
	}

	@Override
	public void signup(MemberDto memberDto) throws Exception {
		sqlSession.getMapper(MemberDAO.class).signup(memberDto);
	}

	@Override
	public void modifyMember(MemberDto memberDto) throws Exception {
		sqlSession.getMapper(MemberDAO.class).modifyMember(memberDto);
	}

	@Override
	public void deleteMember(String userid) throws Exception {
		sqlSession.getMapper(MemberDAO.class).deleteMember(userid);
	}

	@Override
	public List<MemberDto> searchMember(Map<String, String> map) throws Exception{
		return sqlSession.getMapper(MemberDAO.class).searchMember(map);
	}

	@Override
	public List<MemberDto> listAll() throws SQLException {
		return sqlSession.getMapper(MemberDAO.class).listAll();
	}
}
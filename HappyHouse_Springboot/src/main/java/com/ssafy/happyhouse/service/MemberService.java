package com.ssafy.happyhouse.service;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.MemberDto;

public interface MemberService {
	public MemberDto login(Map<String, String> map) throws Exception;
	public void signup(MemberDto memberDto) throws Exception;
	public void modifyMember(MemberDto memberDto) throws Exception;
	public void deleteMember(String userid) throws Exception;
	public List<MemberDto> searchMember(Map<String, String> map) throws Exception;
	public List<MemberDto> listAll() throws SQLException;
}

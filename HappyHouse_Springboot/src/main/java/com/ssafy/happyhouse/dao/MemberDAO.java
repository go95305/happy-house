package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.MemberDto;

public interface MemberDAO {
    public MemberDto login(Map<String, Object> map) throws SQLException;
    public void signup(MemberDto memberDto) throws SQLException;
    public void modifyMember(MemberDto memberDto) throws Exception;
    public void deleteMember(String userid) throws Exception;
    public List<MemberDto> searchMember(Map<String, String> map) throws Exception;
    public List<MemberDto> listAll() throws SQLException;
}

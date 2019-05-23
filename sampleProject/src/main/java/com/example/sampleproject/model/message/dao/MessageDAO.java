package com.example.sampleproject.model.message.dao;

import com.example.sampleproject.model.message.dto.MessageVO;

public interface MessageDAO {
	public void create(MessageVO vo);
	public MessageVO readMessage(int mid);
	public void updateMessage(int mid);
}

package com.example.sampleproject.service.message;

import com.example.sampleproject.model.message.dto.MessageVO;

public interface MessageService {
	public void addMessage(MessageVO vo);
	public MessageVO readMessage(String userId, int mid);
}

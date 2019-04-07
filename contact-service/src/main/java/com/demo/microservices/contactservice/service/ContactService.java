package com.demo.microservices.contactservice.service;

import java.util.List;
import com.demo.microservices.contactservice.model.ContactUsInfo;

public interface ContactService {
  List<ContactUsInfo> getAllMessages();

  ContactUsInfo createMessage(ContactUsInfo info);

  ContactUsInfo getMessageById(Integer id);

  ContactUsInfo updateMessage(ContactUsInfo info);

  void deleteMessage(Integer id);
}

package com.xj.book.home.dao.mongo;

import com.xj.book.home.model.mongo.LoginHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginHistoryDao extends MongoRepository<LoginHistory, String> {
}

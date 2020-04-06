package com.iot.homeAutomation.User;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.iot.homeAutomation.User.DTO.UserDTO;

@Service
public class UserRepositoryImpl implements UserRepository {

	@Resource
	MongoOperations mongoOperations;

	@Override
	public boolean checkAndAddUser(UserDTO user) throws Exception {
		boolean lReturn = false;
		try {
			Query query = new Query(Criteria.where("userId").is(user.getUserId()));
			UserDTO userDTO = mongoOperations.findOne(query, UserDTO.class);
			if (ObjectUtils.isEmpty(userDTO)) {
				mongoOperations.save(user);
				System.out.println("New user saved in DB for userId:- " + user.getUserId());
				lReturn = true;
			} else {
				System.out.println("User already present in DB for userId:- " + user.getUserId());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return lReturn;
	}

}

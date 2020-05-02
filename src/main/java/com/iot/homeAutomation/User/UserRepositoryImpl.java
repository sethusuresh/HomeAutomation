package com.iot.homeAutomation.User;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.iot.homeAutomation.User.DTO.UserDTO;

@Service
public class UserRepositoryImpl implements UserRepository {

	private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);
	
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
				logger.debug("New user saved in DB for userId:- {}", user.getUserId());
				lReturn = true;
			} else {
				logger.debug("User already present in DB for userId:- {}", user.getUserId());
			}
		} catch (Exception e) {
			logger.error("Error in checkAndAddUser for userId:- {}", user.getUserId(), e);
			throw new Exception();
		}
		return lReturn;
	}

	@Override
	public UserDTO findUserById(String userId) throws Exception {
		UserDTO user = new UserDTO();
		try {
			Query query = new Query(Criteria.where("userId").is(userId));
			user = mongoOperations.findOne(query, UserDTO.class);
			logger.debug("User fetched from DB for userId:- {}", userId);
		} catch (Exception e) {
			logger.debug("Error finding user from DB for user Id:- {}", userId);
			throw new Exception();
		}
		return user;
	}
	
	@Override
	public void saveUser(UserDTO user) throws Exception {
		try {
			mongoOperations.save(user);
			logger.debug("User save in DB for userId:- {}", user.getUserId());
		} catch (Exception e) {
			logger.debug("Error saving user to DB for userId:- {}", user.getUserId());
			throw new Exception();
		}
	}

}

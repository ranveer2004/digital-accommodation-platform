package com.stayen.casa.authenticationservice.entity;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("user_credentials")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCredential extends BaseTimestampEntity {
	
	@Id
	@Field(name = "uid")
	private String uid;
	
	@Indexed(unique = true)
	private String email;
	
	private String passwordHash;
	
//	public UserCredential(String uid, String email, String passwordHash) {
//		super.currentTimestamp();
//		this.uid = uid;
//		this.email = email;
//		this.passwordHash = passwordHash;
//	}
	
//	public UserCredential(String uid, String email, String passwordHash, LocalDateTime createdAt, LocalDateTime updatedAt) {
//		super.updateTimestamp(createdAt, updatedAt);
//		this.uid = uid;
//		this.email = email;
//		this.passwordHash = passwordHash;
//	}

	public void updatePassword(String passwordHash) {
		this.passwordHash = passwordHash;
		this.setUpdatedAt(LocalDateTime.now());
	}

	@Override
	public String toString() {
		return "UserCredential [uid=" + uid + ", email=" + email + ", passwordHash=" + passwordHash
				+ ", getCreatedAt()=" + getCreatedAt() + ", getUpdatedAt()=" + getUpdatedAt() + "]";
	}

}

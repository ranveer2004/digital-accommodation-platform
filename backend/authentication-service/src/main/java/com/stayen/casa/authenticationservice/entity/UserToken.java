package com.stayen.casa.authenticationservice.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("user_tokens")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserToken extends BaseTimestampEntity {

	@Id
	@Field(name = "uid")
	private String uid;
	
	@Indexed(unique = true)
	private String email;
	
	private List<DeviceToken> tokens = new ArrayList<>();

	public UserToken(String uid, String email) {
		super(); // current timestamp
		this.uid = uid;
		this.email = email;
	}

	public UserToken(String uid, String email, List<DeviceToken> tokens, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super(createdAt, updatedAt); // timestamp from database
		this.uid = uid;
		this.email = email;
		this.tokens = tokens;
	}

//	public UserToken(String uid, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
//		super.updateTimestamp(createdAt, updatedAt);
//		this.uid = uid;
//		this.email = email;
//	}
	
	/**
	 * Helper Functions
	 */
	public void updateTimestamp() {
		this.setUpdatedAt(LocalDateTime.now());
	}
	
	/**
	 * Check if token present for the given deviceId.
	 * 
	 * @param deviceId
	 * @return true if any token were removed, else false
	 */
	public boolean removeDeviceToken(String deviceId) {
		return this.tokens
				.removeIf((deviceToken) -> deviceToken.getDeviceId().equals(deviceId));
	}
	
	public void addDeviceToken(DeviceToken deviceToken) {
		this.tokens.add(deviceToken);
	}
	
	/**
	 * Rotation of Refresh Token Process
	 * 
	 * @param token
	 * @return
	 */
	public boolean isLastRefreshToken(String token) {
		return this.tokens
				.stream()
				.anyMatch((deviceToken) -> {
					return deviceToken.getLastRefreshToken().equals(token);
				});
	}
	
	/**
	 * Check and rotate current refresh token
	 * 
	 * @param token
	 * @return false if no device token found or rotation is not possible, 
	 * 		   <br/>true if token matched and rotated with lastRefreshToken, 
	 */
	public boolean findTokenAndRotate(String token) {
		return this.tokens
				.stream()
				.anyMatch((deviceToken) -> {
					if(deviceToken.getRefreshToken().equals(token)) {
						deviceToken.setLastRefreshToken(token);
						return true;
					}
					return false;
				});
	}

	/**
	 * Updating newly generated refresh token,
	 * for future use of refreshing the token
	 *
	 * @param deviceId
	 * @param newRefreshToken
	 */
	public void setNewRefreshToken(String deviceId, String newRefreshToken) {
		this.tokens
			.forEach((deviceToken) -> {
				if(deviceToken.getDeviceId().equals(deviceId)) {
					deviceToken.setRefreshToken(newRefreshToken);
				}
			});
	}

}

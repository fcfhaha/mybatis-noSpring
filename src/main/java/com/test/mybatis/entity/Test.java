/*
 * Delta CONFIDENTIAL
 *
 * (C) Copyright Delta Electronics, Inc. 2016 All Rights Reserved
 *
 * NOTICE:  All information contained herein is, and remains the
 * property of Delta Electronics. The intellectual and technical
 * concepts contained herein are proprietary to Delta Electronics
 * and are protected by trade secret, patent law or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Delta Electronics.
 */

package com.test.mybatis.entity;

/**
 * @author V.Caifeng.Fan
 *
 */
public class Test {

	private int id;

	private String name;

	private int score;

	/**
	 * @return the id
	 */
	public int getId() {

		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {

		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {

		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {

		this.name = name;
	}

	/**
	 * @return the score
	 */
	public int getScore() {

		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(int score) {

		this.score = score;
	}

}

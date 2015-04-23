package Acceptance_tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import Domain.Forum_component.Forum_Ruels;
import Domain.Forum_component.Forum_System;
import Domain.Forum_component.Forum;
import Domain.Forum_component.Forum_Ruels;
import Domain.Forum_component.Forum_System;
import Domain.Forum_component.Sub_Forum;
import Domain.User_component.Member;
import Domain.User_component.User;
import Domain.User_component.Member;
import Domain.User_component.Super_Admin;
import Service.Bridge;
import Service.Driver;

public class TC7_create_sub_forum {
	private Forum_System fs;
	private Super_Admin sa;
	private Forum f;
	private Forum_Ruels fr;
	private Member admin;
	private Vector<Member> moderators ;
	private Vector<Member> admins;
	private Bridge b = Driver.getBridge();

	@Before
	public void setUp() throws Exception {
		this.sa = new Super_Admin("super", "qwerty", "workshopIsFun@gmail.com",
				20.0);
		this.fs = new Forum_System((Super_Admin) sa);

		admins = new Vector<>();
		admin = new Member("liran", "qwerty", "mail", 30.0);
		admins.add((Member) admin);
		admins.add(new Member("grey", "qwerty", "mail", 30.0));
		admins.add(new Member("shirt", "qwerty", "mail", 30.0));
		this.fr = new Forum_Ruels();

		b.addForum(fs, "name", "subject", admins, fr);
		this.f = this.fs.getForums().get(0);
		
		moderators = new Vector<>();
		moderators.add((Member) admin);
		
	}

	@Test /*TR 32*/
	public void test_subject() {
		assertFalse(b.createSubForum(f, "food", null, moderators, (User) admin));
	}
	
	@Test /*TR 33*/
	public void test_name() {
		assertFalse(b.createSubForum(f, null, "pizza", moderators, (User) admin));
	}
	
	
	@Test /*TR 34*/
	public void test_moderators() {
		assertFalse(b.createSubForum(f, "food", "pizza", null, (User) admin));
	}
	
	@Test /*TR 37*/
	public void test_sub_forum_added() {
		Sub_Forum subForum;
		subForum = f.createSubForum("food", "pizza", moderators);
		assertTrue(f.getSubs((User) admin).contains(subForum));
	}

}

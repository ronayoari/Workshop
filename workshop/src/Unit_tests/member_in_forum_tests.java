package Unit_tests;

import java.util.Vector;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import Domain.Forum_component.Forum;
import Domain.Forum_component.Forum_Ruels;
import Domain.User_component.Member;
import Domain.User_component.MemberInForum;

public class member_in_forum_tests extends TestCase {

	private Member m;
	private Forum f;
	private MemberInForum mif;
	@Before
	public void setUp() throws Exception {
		m = new Member("liran gret shirt", "qwerty","liran@gmail.com",30);
		Vector<Member> admins = new Vector<Member>();
		Member grey =new Member("grey", "456", "gery@gmail.com", 16);
		Member shirt = new Member("shirt", "789","shirt@gmail.com", 18);
		admins.add(grey);
		admins.add(shirt);
		this.f = new Forum("name", "subject", admins, new Forum_Ruels(), null);
		f.register(m);
		f.login(m.getName(), m.getPassword());
		mif = m.getMembersInForum(f);
	}

	@Test //1
	public void test_set_connected(){
		assertTrue(mif.getConnected());

		mif.setConnected(false);
		assertFalse(mif.getConnected());
	}
	
	@Test //2
	public void test_online_time(){
		assertTrue(mif.getConnected());
		for(int i=0; i<999999999;i++){}
		assertNotSame(this.mif.getOnlineTime(),0);
	}
	
	

	
}
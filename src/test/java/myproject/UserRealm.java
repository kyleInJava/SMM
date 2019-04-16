package myproject;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.kyle.base.user.dao.SysUserMapper;
import com.kyle.base.user.entity.SysUser;

public class UserRealm extends AuthorizingRealm {
	
	@Resource
	private SysUserMapper userMapper;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//从principals获取身份信息
		//将getPrimaryPrincipal方法返回值转为真实类型
		//(在上边的doGetAuthenticationInfo认证通过后填充到SimpleAuthenticationInfo中身份类型)
		String userCode = (String) principals.getPrimaryPrincipal();
		
		//根据身份信息从数据库中获取权限信息
		//模拟从数据库中取到的数据
		List<String>  permissions = new ArrayList<String>();
		permissions.add("user:create");//用户创建
		permissions.add("items:add");//商品添加权限
		//....
		
		//查询到权限数据，返回授权信息(要包括上边的permissions)
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		//将上边查询到的授权信息填充到simpleAuthorizationInfo对象中
		simpleAuthorizationInfo.addStringPermissions(permissions);
		
		return simpleAuthorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		String principal = (String)token.getPrincipal();
		SysUser user = userMapper.getUserByAccount(principal);
		if(user == null){
			throw new UnknownAccountException("没有找到该账号"); 
		}
		
		String password = user.getPassword();
		String salt = user.getSalt();
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, password,
				ByteSource.Util.bytes(salt+principal), this.getName());
		
		return simpleAuthenticationInfo;
		
	}

}

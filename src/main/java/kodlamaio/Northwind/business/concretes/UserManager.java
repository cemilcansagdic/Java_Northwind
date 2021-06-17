package kodlamaio.Northwind.business.concretes;

import org.springframework.stereotype.Service;

import kodlamaio.Northwind.business.abstracts.UserService;
import kodlamaio.Northwind.core.entities.User;
import kodlamaio.Northwind.core.utilities.results.DataResult;
import kodlamaio.Northwind.core.utilities.results.Result;
import kodlamaio.Northwind.core.utilities.results.SuccessDataResult;
import kodlamaio.Northwind.core.utilities.results.SuccessResult;
import kodlamaio.Northwind.dataAccess.abstracts.UserDao;

@Service
public class UserManager implements UserService{
	
	private UserDao userDao;

	public UserManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public Result add(User user) {
		// TODO Auto-generated method stub
		this.userDao.save(user);
		return new SuccessResult("Kullanıcı Eklendi.");
	}
	
	@Override
	public DataResult<User> findByEmail(String email) {
		// TODO Auto-generated method stub
		return new SuccessDataResult<User>(this.userDao.findByEmail(email),"Kullanıcı Bulundu");
	}

}

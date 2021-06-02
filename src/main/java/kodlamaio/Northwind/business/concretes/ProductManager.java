package kodlamaio.Northwind.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.Northwind.business.abstracts.ProductService;
import kodlamaio.Northwind.core.utilities.results.DataResult;
import kodlamaio.Northwind.core.utilities.results.Result;
import kodlamaio.Northwind.core.utilities.results.SuccessDataResult;
import kodlamaio.Northwind.core.utilities.results.SuccessResult;
import kodlamaio.Northwind.dataAccess.abstracts.ProductDao;
import kodlamaio.Northwind.entities.concretes.Product;
import net.bytebuddy.asm.Advice.This;

@Service
public class ProductManager implements ProductService{

	private ProductDao productDao;
	
	@Autowired
	public ProductManager(ProductDao productDao) {
		super();
		this.productDao = productDao;
	}

	@Override
	public DataResult<List<Product>> getAll() {
		return new SuccessDataResult<List<Product>>(this.productDao.findAll(),"Data listelendi");
	}

	@Override
	public Result add(Product product) {
		this.productDao.save(product);
		return new SuccessResult("Ürün eklendi");
	}

}

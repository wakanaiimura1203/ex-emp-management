package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

/**
 * @author nemur
 * 管理者情報を操作するサービス
 *
 */

@Service
@Transactional
public class AdministratorService {
	
	@Autowired
	private AdministratorRepository administratorRepository;
	
	// 管理者情報を挿入する。
	public void insert(Administrator administrator) {
		administratorRepository.insert(administrator);
	}
	

}

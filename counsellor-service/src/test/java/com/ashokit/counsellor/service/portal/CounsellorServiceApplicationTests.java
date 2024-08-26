package com.ashokit.counsellor.service.portal;

import com.ashokit.counsellor.service.portal.entity.CounsellorEntity;
import com.ashokit.counsellor.service.portal.exception.EmailOrPasswordMissMatchException;
import com.ashokit.counsellor.service.portal.repo.CounsellorRepository;
import com.ashokit.counsellor.service.portal.service.impl.CounsellorServiceImpl;
import com.ashokit.counsellor.service.portal.utils.PrepareMockObjects;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class CounsellorServiceApplicationTests {
	@Mock
	private CounsellorRepository counsellorRepository;
	@InjectMocks
	private CounsellorServiceImpl counsellorService;

	@Test
	void test_Login(){
		CounsellorEntity counsellor = PrepareMockObjects.getCounsellor();


		when(counsellorRepository.findByEmail(any())).thenReturn(Optional.of(counsellor));
		CounsellorEntity result = this.counsellorService.login(counsellor.getEmail(), counsellor.getPassword());
		assertEquals(result,counsellor);
		verify(counsellorRepository,times(1)).findByEmail(any());

	}
	@Test
	void test_emailAndPasswordWrong(){
		CounsellorEntity counsellor = PrepareMockObjects.getCounsellor();
		when(counsellorRepository.findByEmail(any())).thenThrow(EmailOrPasswordMissMatchException.class);
		EmailOrPasswordMissMatchException exception = assertThrows(EmailOrPasswordMissMatchException.class,()->{
			CounsellorEntity result = this.counsellorService.login(counsellor.getEmail(), counsellor.getPassword());
		});
		assertEquals("Email or Password is wrong",exception.getMessage());
		verify(counsellorRepository,times(1)).findByEmail(any());
	}

}

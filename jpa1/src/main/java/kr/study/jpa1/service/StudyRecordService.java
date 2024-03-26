package kr.study.jpa1.service;

import kr.study.jpa1.domain.Member;
import kr.study.jpa1.domain.StudyRecode;
import kr.study.jpa1.form.StudyForm;
import kr.study.jpa1.repository.StudyRecodeRepositroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudyRecordService {
  private final StudyRecodeRepositroy recodeRepository;


  public void saveRecord(StudyForm form, Member member){
    StudyRecode recode = StudyRecode.createRecord(form, member);
    recodeRepository.save(recode);
  }

  public List<StudyRecode> getAllRecodes(){
    return recodeRepository.selectAll();
  }

  public StudyRecode getOneRecord(Long id){
    Optional<StudyRecode> recode = recodeRepository.findById(id);
    return recode.isPresent() ? recode.get() : null;
  }
  @Transactional
  public void updateRecord( StudyForm form , StudyRecode recode ){
    StudyRecode updateRecode = StudyRecode.modyfiyRecord(form, recode);
    recodeRepository.save(updateRecode);

  }
}

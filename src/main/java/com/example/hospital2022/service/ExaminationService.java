package com.example.hospital2022.service;

import com.example.hospital2022.model.*;
import com.example.hospital2022.repository.ExaminationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExaminationService implements com.example.hospital2022.service.Service<Examination> {
    private final ExaminationRepo examinationRepo;
    private final DiagnosisService diagnosisService;
    private final  DiseaseService diseaseService;
    private final PatientService patientService;
    private final AppointmentService appointmentService;
    @Override
    public List<Examination> list(String title) {
        return null;
    }

    public List<Examination> diseaseList(Long diseaseId){
        if(diseaseId!=null)
        return examinationRepo.findByDiseaseId(diseaseId);
        return examinationRepo.findAll();
    }

    @Override
    public Examination findById(Long id) {
        return examinationRepo.findById(id).orElse(null);
    }

    @Override
    public void save(Examination examination) {
        examinationRepo.save(examination);
    }

    @Override
    public void delete(Examination examination) {
        examination.getAppointment().setExamination(null);
        appointmentService.save(examination.getAppointment());
        examinationRepo.delete(examination);
    }

    @Override
    public Examination findByTitle(String title) {
        return null;
    }

    public void correct(Examination examination, Appointment appointment, Patient patient, Doctor doctor, String description, LocalDate dateOfDisease,
                        String epicrisis, String recommendation, String document, String diseaseString,
                        String form, String diagnosisString){
        save(examination);
        examination.setDescription(description);
        examination.setDateOfDisease(dateOfDisease);
        examination.setEpicrisis(epicrisis);
        examination.setRecommendation(recommendation);
        examination.setDocument(document);
        Disease disease = new Disease();
        disease.setTitle(diseaseString);
        disease.setForm(form);
        diseaseService.save(disease);
        Diagnosis diagnosis = diagnosisService.findByTitle(diagnosisString);
        if(diagnosis==null){
            diagnosis=new Diagnosis();
            diagnosis.setTitle(diagnosisString);
            diagnosisService.save(diagnosis);
        }
        disease.setDiagnosis(diagnosis);
        disease.setPatient(patient);
        patient.getDiseases().add(disease);
        patient.getDiagnoses().add(diagnosis);
        disease.getExamination().add(examination);
        examination.setDisease(disease);
        examination.setAppointment(appointment);
        appointment.setExamination(examination);
        doctor.getPatients().add(patient);
        patient.getDoctors().add(doctor);
        save(examination);
        diseaseService.save(disease);
        patientService.save(patient);
        appointmentService.save(appointment);
    }

    public List<Examination> doctorList(Doctor doctor, String title) {
        if(title!=null){
            return examinationRepo.findByAppointmentDoctorAndDiseaseTitleContains(doctor,title);
        }
        return examinationRepo.findByAppointmentDoctor(doctor);
    }
}

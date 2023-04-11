
package com.OnlineMedicineShoppingSystem.service;
 

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import com.OnlineMedicalShop.model.Medicine;
import com.OnlineMedicalShop.repository.medicineRepository;
import com.OnlineMedicalShop.service.MedicineServiceImpl;
 
public class MedicineServiceImplTest {
 
    @InjectMocks
    MedicineServiceImpl medicineService;
 
    @Mock
    medicineRepository medicineRepository;
 
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    public void testAddMedicine() {
        Medicine medicine = new Medicine(1, "Aspirin", 10.0f, "For headache", "In stock");
        when(medicineRepository.save(any(Medicine.class))).thenReturn(medicine);
 
        Medicine savedMedicine = medicineService.addMedicine(medicine);
 
        assertEquals(medicine, savedMedicine);
    }
 
    @Test
    public void testGetAllMedicine() {
        List<Medicine> medicines = new ArrayList<>();
        medicines.add(new Medicine(1, "Aspirin", 10.0f, "For headache", "In stock"));
        medicines.add(new Medicine(2, "Ibuprofen", 15.0f, "For pain relief", "Out of stock"));
        when(medicineRepository.findAll()).thenReturn(medicines);
 
        List<Medicine> retrievedMedicines = medicineService.getAllMedicine();
 
        assertEquals(2, retrievedMedicines.size());
    }
 
    @Test
    public void testUpdateMedicine() {
        int medicineId = 1;
        Medicine medicineToUpdate = new Medicine(medicineId, "Aspirin", 10.0f, "For headache", "In stock");
        Medicine updatedMedicine = new Medicine(medicineId, "Ibuprofen", 15.0f, "For pain relief", "Out of stock");
 
        when(medicineRepository.getById(medicineId)).thenReturn(medicineToUpdate);
        when(medicineRepository.save(any(Medicine.class))).thenReturn(updatedMedicine);
 
        boolean isUpdated = medicineService.updateMedicine(updatedMedicine, medicineId);
 
        assertEquals(true, isUpdated);
    }
 
    @Test
    public void testDeleteMedicine() {
        int medicineId = 1;
        doNothing().when(medicineRepository).deleteById(medicineId);
 
        boolean isDeleted = medicineService.deleteMedicine(medicineId);
 
        assertEquals(true, isDeleted);
    }
}



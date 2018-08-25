package com.innvent.medicspot.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MedicineDTO {
	
	Integer total;
	Integer offset;
	Integer limit;
	List<Medicine> result;

}

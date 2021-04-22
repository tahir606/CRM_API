package com.example.CRM.Rights.RightsChart;

import jdk.internal.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RightsRepository extends JpaRepository<RightChart,Integer> {
    List<RightChart> findAllByUserCode(int userCode);
    RightChart deleteByUserCodeAndRightsCode(int userCode,int rightCode);
}

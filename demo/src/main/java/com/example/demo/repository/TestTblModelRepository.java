package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TestTblModel;
import com.example.demo.entity.pk.TestTblModelId;

@Repository
public interface TestTblModelRepository extends JpaRepository<TestTblModel, TestTblModelId>, TestTblModelRepositoryCustom, JpaSpecificationExecutor<TestTblModel> {
}

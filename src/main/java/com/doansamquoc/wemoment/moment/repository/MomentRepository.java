package com.doansamquoc.wemoment.moment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doansamquoc.wemoment.moment.entity.Moment;

public interface MomentRepository extends JpaRepository<Moment, String> {

}

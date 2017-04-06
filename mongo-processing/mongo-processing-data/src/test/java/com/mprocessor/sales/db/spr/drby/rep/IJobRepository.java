package com.mprocessor.sales.db.spr.drby.rep;


import com.mprocessor.sales.db.mdl.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJobRepository extends CrudRepository<Job,String>{
}

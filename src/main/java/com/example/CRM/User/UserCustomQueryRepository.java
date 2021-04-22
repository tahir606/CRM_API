package com.example.CRM.User;

import com.example.CRM.Email.EmailTicket.EmailTickets;
import org.apache.catalina.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserCustomQueryRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Users> getCountEmailStatus(String filter) {
        Query query = entityManager.createQuery("select new users (u.userCode,u.userName,u.fullName, ( select count(distinct h.hCode ) from history h,email_ticket e where u.userCode=h.userId and e.status =" + 3 + " and h.status =" + 3 + filter + "  )) From users u where u.freeze =" + 0);
        return (List<Users>) query.getResultList();
    }

    public List<Users> averageCalculate() {


        String sql = "SELECT u.FNAME as fullName ,concat (concat ( concat ( substr(SEC_TO_TIME(avg(TIME_TO_SEC((TimeDiff( solvedTime, lockedTime))))),1,2) , ' Hours ') , concat ( substr(SEC_TO_TIME(avg(TIME_TO_SEC((TimeDiff( solvedTime, lockedTime))))),4,2) , ' Minutes ') ) ,concat ( substr(SEC_TO_TIME(avg(TIME_TO_SEC((TimeDiff( solvedTime, lockedTime))))),7,2) , ' Sec ')) as availableString      from ( select  h.userId as id,h.time as lockedTime, h1.time  as solvedTime  FROM email_ticket e, history h ,history h1 ,users c where h1.status =3 and  h1.emailId=e.email_no  and h1.userId =c.UCODE and h.status =2 and  h.emailId=e.email_no and h.userId =c.UCODE) as tamp,users u WHERE u.UCODE =id group by u.FNAME";
        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> authors = query.getResultList();
        List<Users> usersList = new ArrayList<>();
        for (Object[] a : authors) {
            usersList.add(new Users(String.valueOf(a[0]), String.valueOf(a[1])));
        }


        return usersList;
    }
}

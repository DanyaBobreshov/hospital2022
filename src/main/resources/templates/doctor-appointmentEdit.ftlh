<#import "blocks/template.ftlh" as t>
<@t.template user>
<appointment>
    <hr>
        <form action="doctor-appointmentEdit/{appointment.id}" method="post">
                         <div class="form-group">
                             <label for="date">Дата</label>
                             <input type="date" value="${appointment.date}"required class="form-control"
                                    id="date" name="date">
                         </div>
                         <div class="form-group">
                             <label for="time">Дата</label>
                             <input type="time" value="${appointment.time}"required class="form-control"
                                    id="time" name="time">
                         </div>
                         <div class="form-group">
                             <label for="cabinet">Кабинет</label>
                             <input type="cabinet" value="${appointment.cabinet}"required class="form-control"
                                    id="cabinet" name="cabinet">
                         </div>
     <h4 style="text-align: center">${appointment.doctor.user.secondName}</h4><hr>
     <else>
               <h6>Доктор не назначен<h6>
     <h4 style="text-align: center">${appointment.patient.user.secondName}</h4><hr>
     <else>
               <h6>Пациент не назначен<h6>
      </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" style="width: 100%" class="btn btn-dark">Редактировать</button>
         </form>
         </>
     </@t.template>

<#import "blocks/template.ftlh" as t>
<@t.template user>
<doctor>
    <hr>
        <form action="doctor-edit/${doctor.id}" method="post">
                         <div class="form-group">
                             <label for="name">Имя</label>
                             <input type="text" value="${doctor.user.name}"required class="form-control"
                                    id="name" name="name">
                         </div>
                         <div class="form-group">
                             <label for="secondName">Фамилия</label>
                             <input type="text" value="${doctor.user.secondName}"required class="form-control"
                                    id="secondName" name="secondName">
                         </div>
                          <div class="form-group">
                              <label for="fatherName">Фамилия</label>
                              <input type="text" value="${doctor.user.fatherName}"required class="form-control"
                                     id="fatherName" name="fatherName">
                          </div>
                          <div class="form-group">
                              <label for="dateOfBurn">Дата выявления</label>
                              <input type="date" value="${doctor.user.dateOfBurn}"required class="form-control"
                                     id="dateOfBurn" name="dateOfBurn">
                          </div>
                          <div class="form-group">
                              <label for="telephone">Фамилия</label>
                              <input type="text" value="${doctor.user.telephone}"required class="form-control"
                                     id="telephone" name="telephone">
                          </div>
                           <div class="form-group">
                               <label for="speciality">Фамилия</label>
                               <input type="text" value="${doctor.speciality}"required class="form-control"
                                      id="speciality" name="speciality">
                           </div>
             </div>
               <input type="hidden" name="_csrf" value="${_csrf.token}"/>
               <button type="submit" style="width: 100%" class="btn btn-dark">Редактировать</button>
    </form>
    </>
</@t.template>
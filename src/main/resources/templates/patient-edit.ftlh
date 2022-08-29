<#import "blocks/template.ftlh" as t>
<@t.template user>
<patient>
    <hr>
        <form action="patient-edit/${patient.id}" method="post">
                   <div class="form-group">
                       <label for="secondName">Фамилия</label>
                       <input type="text" value="${patient.user.secondName}" required class="form-control"
                              id="secondName" name="secondName">
                   </div>
                   <div class="form-group">
                      <label for="name">Имя</label>
                      <input type="text" value="${patient.user.name}" required class="form-control"
                      id="name" name="name">
                               </div>
                    <div class="form-group">
                       <label for="fatherName">Отчество</label>
                       <input type="text" value="${patient.user.fatherName}" required class="form-control"
                       id="fatherName" name="fatherName">
                       </div>
                        <div class="form-group">
                        <label for="telephone">Телефон</label>
                        <input type="text" value="${patient.user.telephone}" required class="form-control"
                        id="telephone" name="telephone">
                        </div>
                    <div class="form-group">
                       <label for="dateOfBurn">Дата рождения</label>
                       <input type="date" value="${patient.user.dateOfBurn}" required class="form-control"
                       id="dateOfBurn" name="dateOfBurn">
                       </div>
                   </div>
                   <input type="hidden" name="_csrf" value="${_csrf.token}"/>
               <button type="submit" style="width: 100%" class="btn btn-dark">Редактировать</button>
    </form>
    </>
</@t.template>
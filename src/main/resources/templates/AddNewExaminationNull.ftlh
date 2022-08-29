<#import "blocks/template.ftlh" as t>
<@t.template user>
<date>
<time>
    <h3 style="text-align: center">Прием пациента</h1><hr>

    <hr>
        <form action="/AddNewExaminationNull" method="post">
                         <div class="form-group">
                             <label for="doctor">Лечащий врач</label>
                             <input type="text" value="${user.secondName}"required class="form-control"
                                    id="doctor" name="doctor">
                         </div>
                         <div class="form-group">
                             <label for="patient">Пациент</label>
                             <input type="text" required class="form-control"
                                    id="patient" name="patient">
                         </div>
                         <div class="form-group">
                             <label for="date">Дата</label>
                             <input type="date" value="${date}"required class="form-control"
                                    id="date" name="date">
                         </div>
                         <div class="form-group">
                             <label for="time">Время</label>
                             <input type="time" value="${time}"required class="form-control"
                                    id="time" name="time">
                         </div>
                          <div class="form-group">
                              <label for="description">Жалобы</label>
                              <input type="text" required class="form-control"
                                     id="description" name="description">
                          </div>
                          <div class="form-group">
                              <label for="dateOfDisease">Дата выявления</label>
                              <input type="date" required class="form-control"
                                     id="dateOfDisease" name="dateOfDisease">
                          </div>
                          <div class="form-group">
                              <label for="epicrisis">Заключение</label>
                              <input type="text" required class="form-control"
                                     id="epicrisis" name="epicrisis">
                          </div>
                           <div class="form-group">
                               <label for="recommendation">Назначенное лечение</label>
                               <input type="text" required class="form-control"
                                      id="recommendation" name="recommendation">
                           </div>
                           <div class="form-group">
                               <label for="document">Справка</label>
                               <input type="text" required class="form-control"
                                      id="document" name="document">
                           </div>
                           <div class="form-group">
                               <label for="disease">Заболевание</label>
                               <input type="text" required class="form-control"
                                      id="disease" name="disease">
                           </div>
                           <div class="form-group">
                               <label for="form">В какой форме протекает</label>
                               <input type="text" required class="form-control"
                                      id="form" name="form">
                           </div>
                           <div class="form-group">
                               <label for="diagnosis">Предварительный диагноз (ФОРМАЛИЗОВАННО)</label>
                               <input type="text" required class="form-control"
                                      id="diagnosis" name="diagnosis">
                           </div>
             </div>
               <input type="hidden" name="_csrf" value="${_csrf.token}"/>
               <button type="submit" style="width: 100%" class="btn btn-dark">Завершить прием</button>
    </form>
    </>
    </>
</@t.template>
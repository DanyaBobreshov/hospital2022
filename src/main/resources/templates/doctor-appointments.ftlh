<#import "blocks/template.ftlh" as t>
<@t.template user>
<doctorName>
<date>
<h1 style="text-align: center">Ваша неделя</h1><hr>
    <h4 style="text-align: center">${doctorName}</h4><hr>
    <h4 style="text-align: center">${date}</h4><hr>
    </doctorName>
    </date>
    <div style="float:left">
     <table class="table table-hover table-dark">
                <tr>
                    <th scope="col">Понедельник</th>
                </tr>
                <date1>
                <tr>
                    <th scope="col">${date1}</th>
                </tr>
                </date1>
                <#list appointments1 as appointment>
                <tr>
                    <th><a href="/doctor-appointmentEdit/${appointment.id}">${appointment.id}</th>
                </tr>
          <#else>
          <h6>Записей нет<h6>
     </#list>
    </table>
            </div>
    <div style="float:left">
         <table class="table table-hover table-dark">
                    <tr>
                        <th scope="col">Вторник</th>
                    </tr>
                    <date2>
                    <tr>
                        <th scope="col">${date2}</th>
                    </tr>
                    </date2>
                    <#list appointments2 as appointment>
                    <tr>
                        <th>${appointment.time}</th>
                    </tr>
              <#else>
              <h6>Записей нет<h6>
         </#list>
        </table>
        </div>
        <div style="float:left">
             <table class="table table-hover table-dark">
                        <tr>
                            <th scope="col">Среда</th>
                        </tr>
                        <date3>
                        <tr>
                            <th scope="col">${date3}</th>
                        </tr>
                        </date3>
                        <#list appointments3 as appointment>
                        <tr>
                            <th>${appointment.time}</th>
                        </tr>
                  <#else>
                  <h6>Записей нет<h6>
             </#list>
            </table>
                    </div>
            <div style="float:left">
                 <table class="table table-hover table-dark">
                            <tr>
                                <th scope="col">Четверг</th>
                            </tr>
                            <date4>
                            <tr>
                                <th scope="col">${date4}</th>
                            </tr>
                            </date4>
                            <#list appointments4 as appointment>
                            <tr>
                                <th>${appointment.time}</th>
                            </tr>
                      <#else>
                      <h6>Записей нет<h6>
                 </#list>
                </table>
                        </div>
                <div style="float:left">
                     <table class="table table-hover table-dark">
                                <tr>
                                    <th scope="col">Пятница</th>
                                </tr>
                                <date5>
                                <tr>
                                    <th scope="col">${date5}</th>
                                </tr>
                                </date5>
                                <#list appointments5 as appointment>
                                <tr>
                                    <th>${appointment.time}</th>
                                </tr>
                          <#else>
                          <h6>Записей нет<h6>
                     </#list>
                    </table>
                            </div>
   </@t.template>
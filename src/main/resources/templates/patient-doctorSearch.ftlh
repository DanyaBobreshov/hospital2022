<#import "blocks/template.ftlh" as t>
<@t.template user>
<date>
    <h4 style="text-align: center">${doctor.user.secondName}</h4><hr>
    <div style="float:left">
     <table class="table table-hover table-dark">
                <tr>
                    <th scope="col">Свободное время</th>
                </tr>
                <#list timeList as time>
                <tr>
                    <th><a href="/patient-appointmentAdd/${searchWord}/${time}/${doctor.id}">${time}</th>
                </tr>
          <#else>
          <h6>Записей нет<h6>
     </#list>
    </table>
        <form method="get" action="/patient-doctorSearch/${doctor.id}">
                <div class="form-group col-md-10">
                    <label for="searchWord">Поиск по дате</label>
                    <input type="date" name="searchWord" <#if searchWord??>value="${searchWord}"</#if> class="form-control"
                           id="searchWord">
                </div>
                    <button style="width: 100%" type="submit" class="btn btn-dark">Поиск</button>
            </form>
        <hr>
        </>
       </@t.template>
<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Ваши пациенты</h1><hr>
     <table class="table table-hover table-dark">
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">Фамилия</th>
                    <th scope="col">Имя</th>
                    <th scope="col">Отчество</th>
                    <th scope="col">Дата рождения</th>
                    <th scope="col">Номер страхового полиса</th>
                </tr>
                <#list patients as patient>
                <tr>
                    <th><a href="/patient/${patient.id}">${patient.id}</th>
                    <th>${patient.user.secondName}</th>
                    <th>${patient.user.name}</th>
                    <th>${patient.user.fatherName}</th>
                    <th>${patient.user.dateOfBurn}</th>
                    <th>${patient.user.login}</th>
                    <th><a href="/patients/edit/${patient.id}">Редактировние</a></th>
                    <th><a href="/patients/delete/${patient.id}">Удаление</a></th>
                </tr>
                <#else>
                <h3>Пациентов нет</h3>
            </#list>
            </table>
    <form method="get" action="/doctor-show-patients">
            <div class="form-group col-md-10">
                <label for="searchWord">Поиск по фамилии</label>
                <input type="text" name="searchWord" <#if searchWord??>value="${searchWord}"</#if> class="form-control"
                       id="searchWord">
            </div>
            <button style="width: 100%" type="submit" class="btn btn-dark">Поиск</button>
    </form>
    <hr>
 <hr>
    </@t.template>
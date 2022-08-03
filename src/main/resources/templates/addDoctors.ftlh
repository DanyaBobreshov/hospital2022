<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Наши доктора</h1><hr>
     <table class="table table-hover table-dark">
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">Фамилия</th>
                    <th scope="col">Имя</th>
                    <th scope="col">Отчество</th>
                    <th scope="col">Специальность</th>
                    <th scope="col">Дата рождения</th>
                    <th scope="col">Телефонный номер</th>
                </tr>
                <#list doctors as doctor>
                <tr>
                    <th>${doctor.id}</th>
                    <th>${doctor.user.secondName}</th>
                    <th>${doctor.user.name}</th>
                    <th>${doctor.user.fatherName}</th>
                    <th>${doctor.speciality}</th>
                    <th>${doctor.user.dateOfBurn}</th>
                    <th>${doctor.user.telephone}</th>
                    <th><a href="/addDoctors/edit/${doctor.id}">Редактировние</a></th>
                    <th><a href="/addDoctors/delete/${doctor.id}">Удаление</a></th>
                </tr>
                <#else>
                <h3>Организаций нет</h3>
            </#list>
            </table>
    <form method="get" action="/addDoctors">
            <div class="form-group col-md-10">
                <label for="searchWord">Поиск по фамилии</label>
                <input type="text" name="searchWord" <#if searchWord??>value="${searchWord}"</#if> class="form-control"
                       id="searchWord">
            </div>
            <button style="width: 100%" type="submit" class="btn btn-dark">Поиск</button>
    </form>
    <hr>
 <hr>
    <a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 100%"
       data-toggle="collapse" href="#collapseExample" role="button"
       aria-expanded="true" aria-controls="collapseExample">
        Добавить Доктора
    </a>
    <div class="collapse mb-3" id="collapseExample" style="">
        <form action="/addDoctors/add" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="userId">ID пользователя</label>
                <input type="Long" required class="form-control"
                       id="userId" name="userId">
            </div>
             <div class="form-group">
                <label for="speciality">Специальность</label>
                <input type="speciality" required class="form-control"
                id="speciality" name="speciality">
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                        <button type="submit" style="width: 100%" class="btn btn-dark">Добавить</button>
        </form>
        <hr>
 </div>
   </@t.template>
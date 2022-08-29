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
                    <th scope="col">Кабинет</th>
                </tr>
                <#list doctors as doctor>
                <tr>
                    <th><a href="/patient-doctorSearch/${doctor.id}">${doctor.id}</th>
                    <th>${doctor.user.secondName}</th>
                    <th>${doctor.user.name}</th>
                    <th>${doctor.user.fatherName}</th>
                    <th>${doctor.speciality}</th>
                    <th>${doctor.cabinet}</th>
                </tr>
                <#else>
                <h3>Доктора нет</h3>
            </#list>
            </table>
    <form method="get" action="/patient-searchDoctor">
            <div class="form-group col-md-10">
                <label for="searchWordName">Поиск по фамилии</label>
                <input type="text" name="searchWordName" <#if searchWordName??>value="${searchWordName}"</#if> class="form-control"
                       id="searchWordName">
            </div>
                <div class="form-group col-md-10">
                    <label for="searchWordSpec">Поиск по специальности</label>
                    <input type="text" name="searchWordSpec" <#if searchWordSpec??>value="${searchWordSpec}"</#if> class="form-control"
                           id="searchWordSpec">
                </div>
                <button style="width: 100%" type="submit" class="btn btn-dark">Поиск</button>
        </form>
    <hr>
        </@t.template>
<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Мои приемы</h1><hr>
    <h4 style="text-align: center">${user.secondName}</h1><hr>
     <table class="table table-hover table-dark">
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">Заболевание</th>
                    <th scope="col">Пациент</th>
                    <th scope="col">Дата приема</th>
                    <th scope="col">Жалобы</th>
                    <th scope="col">Дата заболевания</th>
                    <th scope="col">Объективный осмотр</th>
                    <th scope="col">Результаты лечения</th>
                    <th scope="col">Документ</th>
                </tr>
                <#list examinations as examination>
                <tr>
                    <th>${examination.id}</th>
                    <th><a href="/disease/${examination.disease.id}">${examination.disease.title}</th>
                    <th><a href="/patient/${examination.appointment.patient.id}">${examination.appointment.patient.user.secondName}</th>
                    <th>${examination.appointment.date}</th>
                    <th>${examination.description}</th>
                    <th>${examination.dateOfDisease}</th>
                    <th>${examination.epicrisis}</th>
                    <th>${examination.recommendation}</th>
                    <th>${examination.document}</th>
                    <th><a href="/examination-edit/${examination.id}">Редактировние</a></th>
                    <th><a href="/examination/delete/${examination.id}">Удаление</a></th>
                </tr>
                <#else>
                <h3>Приемов нет</h3>
            </#list>
            </table>
            <form method="get" action="/doctor-examination">
                        <div class="form-group col-md-10">
                            <label for="searchWord">Поиск по заболеваниям</label>
                            <input type="text" name="searchWord" <#if searchWord??>value="${searchWord}"</#if> class="form-control"
                                   id="searchWord">
                        </div>
                        <button style="width: 100%" type="submit" class="btn btn-dark">Поиск</button>
                </form>
                <hr>
   </@t.template>
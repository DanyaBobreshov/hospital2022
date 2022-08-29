<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Мои Приемы</h1><hr>
     <table class="table table-hover table-dark">
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">Заболевание</th>
                    <th scope="col">Диагноз</th>
                    <th scope="col">Форма</th>
                    <th scope="col">Дата Заболевания</th>
                    <th scope="col">Дата Приема</th>
                    <th scope="col">Время Приема</th>
                    <th scope="col">Жалобы</th>
                    <th scope="col">Заключение</th>
                    <th scope="col">Лечение</th>
                    <th scope="col">Справка</th>
                </tr>
                <#list examinations as examination>
                <tr>
                    <th>${examination.id}</th>
                    <th>${examination.disease.title}</th>
                    <th>${examination.disease.diagnosis.title}</th>
                    <th>${examination.disease.form}</th>
                    <th>${examination.dateOfDisease}</th>
                    <th>${examination.appointment.date}</th>
                    <th>${examination.appointment.time}</th>
                    <th>${examination.description}</th>
                    <th>${examination.epicrisis}</th>
                    <th>${examination.recommendation}</th>
                    <th>${examination.document}</th>
                </tr>
                <#else>
                <h3>Записей нет</h3>
            </#list>
            </table>
    <form method="get" action="/disease-examination">
            <button style="width: 100%" type="submit" class="btn btn-dark">Загрузить</button>
    </form>
    <hr>
 <hr>
 </@t.template>
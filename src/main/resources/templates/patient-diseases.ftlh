<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Мои Заболевания</h1><hr>
     <table class="table table-hover table-dark">
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">Наименование</th>
                    <th scope="col">Диагноз</th>
                    <th scope="col">Форма</th>
                    <th scope="col">Приемы</th>
                </tr>
                <#list diseases as disease>
                <tr>
                    <th>${disease.id}</th>
                    <th>${disease.title}</th>
                    <th>${disease.diagnosis.title}</th>
                    <th>${disease.form}</th>
                    <th><a href="/disease-examination/${disease.id}">Приемы</a></th>
                </tr>
                <#else>
                <h3>Записей нет</h3>
            </#list>
            </table>
    <form method="get" action="/patient-diseases">
            <button style="width: 100%" type="submit" class="btn btn-dark">Загрузить</button>
    </form>
    <hr>
 <hr>
 </@t.template>
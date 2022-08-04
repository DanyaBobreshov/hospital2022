<#import "blocks/template.ftlh" as t>
<@t.template user>

<h1 style="text-align: center">Наши доктора</h1><hr>
     <table class="table table-hover table-dark">
                <tr>
                    <th scope="col">id</th>
                </tr>
                <#list day1.appointments as appointment>
                <tr>
                    <th>${appointment.time}</th>
                </tr>
     </#list>
</table>
   </@t.template>
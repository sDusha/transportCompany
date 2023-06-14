<#import "blocks/template.ftlh" as t>
<@t.template user>
<h3>Редактирование пользователя ${user.name}</h3>
<form action="/admin/user/edit" method="post">
    <#list roles as role>
        <div>
            <div class="form-check">
                <input id="${role}" name="status" type="radio" class="form-check-input" checked="" required="">
                <label class="form-check-label" for="${role}">${role}</label>
            </div>
        </div>
    </#list>
    <input type="hidden" value="${user.id}" name="userId">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit">Save</button>
</form>
</@t.template>
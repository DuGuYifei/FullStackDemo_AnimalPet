import{e as t,F as n,f as l,g as e,o as a,t as o}from"./vendor.ba458dd1.js";const i={data:()=>({animalArr:[]}),methods:{delAnimal(t){!function(t,n){const l=new XMLHttpRequest;l.onreadystatechange=function(){4===this.readyState&&202===this.status&&s(n)},l.open("DELETE","http://localhost:4030/api/animals/"+t,!0),l.send()}(t,this)},goAdd(){this.$router.push("/animals/add")},goView(t){this.$router.push({path:"/animals/view",query:{animal:t}})},goFile(){this.$router.push({path:"/file"})}},mounted:function(){s(this)}};function s(t){const n=new XMLHttpRequest;n.onreadystatechange=function(){4===this.readyState&&200===this.status&&function(t,n){n.animalArr=[],t.animals.forEach((t=>{console.log(t),n.animalArr.push(t.name)}))}(JSON.parse(this.responseText),t)},n.open("GET","http://localhost:4030/api/animals",!0),n.send()}const u=e("h1",null,"Animals",-1),r=e("tr",null,[e("th",null,"name"),e("th",null,"view/edit"),e("th",null,"delete")],-1),d=["onClick"],h=["onClick"],c=e("br",null,null,-1);i.render=function(i,s,m,p,f,g){return a(),t(n,null,[u,r,(a(!0),t(n,null,l(f.animalArr,(n=>(a(),t("tr",null,[e("td",null,o(n),1),e("td",null,[e("button",{onClick:t=>g.goView(n)},"View/Edit",8,d)]),e("td",null,[e("button",{onClick:t=>g.delAnimal(n)},"Delete",8,h)])])))),256)),e("button",{onClick:s[0]||(s[0]=(...t)=>g.goAdd&&g.goAdd(...t))},"add"),c,e("button",{onClick:s[1]||(s[1]=(...t)=>g.goFile&&g.goFile(...t))},"file")],64)};export{i as default};

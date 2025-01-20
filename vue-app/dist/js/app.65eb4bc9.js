(function(){"use strict";var e={1775:function(e,n,t){var i=t(5130),o=t(6768),r=t(4232);const c={id:"app",class:"ceiling-fan"};function s(e,n,t,i,s,a){return(0,o.uX)(),(0,o.CE)("div",c,[n[4]||(n[4]=(0,o.Lk)("img",{src:"/assets/ceiling-fan.png",alt:"My Image",class:"ceiling-fan-image"},null,-1)),(0,o.Lk)("div",null,[(0,o.Lk)("button",{onClick:n[0]||(n[0]=(...e)=>a.increaseSpeed&&a.increaseSpeed(...e))},"Speed"),(0,o.Lk)("button",{onClick:n[1]||(n[1]=(...e)=>a.toggleDirection&&a.toggleDirection(...e))},"Direction")]),(0,o.Lk)("div",null,[(0,o.Lk)("p",null,[n[2]||(n[2]=(0,o.Lk)("strong",null,"Direction:",-1)),(0,o.eW)(" "+(0,r.v_)(s.direction),1)]),(0,o.Lk)("p",null,[n[3]||(n[3]=(0,o.Lk)("strong",null,"Speed:",-1)),(0,o.eW)(" "+(0,r.v_)(s.speed),1)])])])}var a={data(){return{speed:0,direction:"clockwise",fanId:1,mode:"insert"}},mounted(){this.fetchCeilingFan()},computed:{requestMethod(){return"insert"==this.mode?"POST":"PUT"},requestUrl(){return"insert"==this.mode?"http://localhost:8080/api/fans":"http://localhost:8080/api/fans/1"}},methods:{async fetchCeilingFan(){fetch("http://localhost:8080/api/fans").then((e=>e.json())).then((e=>{console.log(e),e.length>0&&(this.speed=e[0].speed,this.direction=e[0].direction,this.mode="update")})).catch((e=>{console.log("Error fetching fan:",e)}))},async increaseSpeed(){const e=this.speed+1>3?0:this.speed+1,n={speed:e,direction:this.direction};await this.updateCeilingFan(n),this.speed=e},async toggleDirection(){const e="clockwise"===this.direction?"counter-clockwise":"clockwise",n={speed:this.speed,direction:e};await this.updateCeilingFan(n),this.direction=e},async updateCeilingFan(e){fetch(this.requestUrl,{method:this.requestMethod,headers:{"Content-Type":"application/json"},body:JSON.stringify(e)}).then((e=>e.json())).then((e=>{console.log(e),e&&(this.speed=e.speed,this.direction=e.direction,this.mode="update")})).catch((e=>{console.log("Error fetching fan:",e)}))}}},u=t(1241);const l=(0,u.A)(a,[["render",s],["__scopeId","data-v-ad427b58"]]);var d=l;(0,i.Ef)(d).mount("#app")}},n={};function t(i){var o=n[i];if(void 0!==o)return o.exports;var r=n[i]={exports:{}};return e[i].call(r.exports,r,r.exports,t),r.exports}t.m=e,function(){var e=[];t.O=function(n,i,o,r){if(!i){var c=1/0;for(l=0;l<e.length;l++){i=e[l][0],o=e[l][1],r=e[l][2];for(var s=!0,a=0;a<i.length;a++)(!1&r||c>=r)&&Object.keys(t.O).every((function(e){return t.O[e](i[a])}))?i.splice(a--,1):(s=!1,r<c&&(c=r));if(s){e.splice(l--,1);var u=o();void 0!==u&&(n=u)}}return n}r=r||0;for(var l=e.length;l>0&&e[l-1][2]>r;l--)e[l]=e[l-1];e[l]=[i,o,r]}}(),function(){t.n=function(e){var n=e&&e.__esModule?function(){return e["default"]}:function(){return e};return t.d(n,{a:n}),n}}(),function(){t.d=function(e,n){for(var i in n)t.o(n,i)&&!t.o(e,i)&&Object.defineProperty(e,i,{enumerable:!0,get:n[i]})}}(),function(){t.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(e){if("object"===typeof window)return window}}()}(),function(){t.o=function(e,n){return Object.prototype.hasOwnProperty.call(e,n)}}(),function(){var e={524:0};t.O.j=function(n){return 0===e[n]};var n=function(n,i){var o,r,c=i[0],s=i[1],a=i[2],u=0;if(c.some((function(n){return 0!==e[n]}))){for(o in s)t.o(s,o)&&(t.m[o]=s[o]);if(a)var l=a(t)}for(n&&n(i);u<c.length;u++)r=c[u],t.o(e,r)&&e[r]&&e[r][0](),e[r]=0;return t.O(l)},i=self["webpackChunkvue_app"]=self["webpackChunkvue_app"]||[];i.forEach(n.bind(null,0)),i.push=n.bind(null,i.push.bind(i))}();var i=t.O(void 0,[504],(function(){return t(1775)}));i=t.O(i)})();
//# sourceMappingURL=app.65eb4bc9.js.map
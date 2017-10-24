import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Subject } from './subject.model';
import { SubjectService } from './subject.service';
import {ProjectService} from "../../entities/project/project.service";
import {Project} from "../../entities/project/project.model";
@Injectable()
export class SubjectPopupService {
    private isOpen = false;
    login ?= 'xxxyxxxx';
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private subjectService: SubjectService,
        private projectService: ProjectService

    ) {}

    open(component: Component, login?: string, isDelete?: boolean , projectName?: string): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (login) {
            this.subjectService.find(login).subscribe((subject) => {
                this.subjectModalRef(component, subject , isDelete );
            });
        } else {
            if(projectName) {
                this.projectService.find(projectName).subscribe((project) => {
                    var subject = new Subject();
                    subject.project = project;
                    return this.subjectModalRef(component, subject , isDelete);
                })
            }
        }
    }

    subjectModalRef(component: Component, subject: Subject , isDelete?: boolean): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.subject = subject;
        modalRef.componentInstance.isDelete = isDelete;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        });
        return modalRef;
    }
}

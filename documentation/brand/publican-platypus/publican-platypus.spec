%define brand platypus

Name:		publican-platypus
Summary:	Common documentation files for %{brand}
Version:	0.1
Release:	0%{?dist}
License:	SETUP: Set This
Group:		Applications/Text
Buildroot:	%{_tmppath}/%{name}-%{version}-%{release}-root-%(%{__id_u} -n)
Buildarch:	noarch
Source:		https://www.SETUP.set.me.example.com/source/%{name}-%{version}.tgz
Requires:	publican >= 1.99
BuildRequires:	publican >= 1.99
URL:		https://www.SETUP.set.me.example.com

%description
This package provides common files and templates needed to build documentation
for %{brand} with publican.

%prep
%setup -q 

%build
publican build --formats=xml --langs=all --publish

%install
rm -rf $RPM_BUILD_ROOT
mkdir -p -m755 $RPM_BUILD_ROOT%{_datadir}/publican/Common_Content
publican install_brand --path=$RPM_BUILD_ROOT%{_datadir}/publican/Common_Content

%clean
rm -rf $RPM_BUILD_ROOT

%files
%defattr(-,root,root,-)
%doc README
%doc COPYING
%{_datadir}/publican/Common_Content/%{brand}

%changelog
* Wed Nov 21 2012  SETUP:YourName <SETUP:your.email@example.com> 0.1
- Created Brand

